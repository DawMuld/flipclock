/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;




/**
 *
 * @author daan-
 */
public class RenderPanel extends JPanel implements Runnable {

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int PERIOD = 32;
    private Thread animator;
    private volatile boolean running;
    private volatile int period = PERIOD;
    private int panelWidth;
    private int panelHeight;
    private BufferedImage bufferImage;
    private BufferRenderer bufferRenderer;
    private BufferedImage bgImage;
    private FlipYield flipYield;






    public RenderPanel() {
        super(null);
        this.panelWidth = this.dim.width;
        this.panelHeight = this.dim.height;
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        this.bufferRenderer = new BufferRenderer(this.panelWidth, this.panelHeight);
        this.bgImage = loadBG();
        initRefreshRate();
        this.flipYield = new FlipYield(3476234);
    }
    public RenderPanel(int value) {
        super(null);
        this.panelWidth = this.dim.width;
        this.panelHeight = this.dim.height;
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        this.bufferRenderer = new BufferRenderer(this.panelWidth, this.panelHeight);
        this.bgImage = loadBG();
        initRefreshRate();
        this.flipYield = new FlipYield(value);
    }






    public RenderPanel(int width, int height) {
        super(null);
        this.panelWidth = width;
        this.panelHeight = height;
        setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        this.bufferRenderer = new BufferRenderer(this.panelWidth, this.panelHeight);
        this.bgImage = loadBG();
        initRefreshRate();
    }






    public void startPanel() {
        if ((this.animator == null) || (!this.animator.isAlive())) {
            this.animator = new Thread(this);
            this.animator.start();
        }
    }






    public void stopPanel() {
        this.running = false;
    }






    public boolean isRunning() {
        return this.running;
    }






    public int getFramePeriod() {
        return this.period;
    }






    public void setFramePeriod(int period) {
        this.period = period;
        System.out.println("RENDER PANEL PERIOD VALUE SET ON: \t" + period);
    }






    @Override
    public void run() {
        this.running = true;
        requestFocusInWindow();
        loopRender();
    }






    @Override
    public void paint(Graphics g) {
        if (this.bufferImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(this.bufferImage, 0, 0, this);
            g2d.dispose();
            g.dispose();
        }
    }






    public void loopRender() {
        long startTime = System.nanoTime();
        long currentTime = startTime;
        long sleepTime = this.period;
        int delayCount = 0;
        while (this.running) {
            long elapsedTime = System.nanoTime() - currentTime;
            currentTime += elapsedTime;
            update(16L);
            render();
            long deltaTime = System.nanoTime() - currentTime;
            sleepTime = this.period - deltaTime / 1000000L;
            if (sleepTime > 0L) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException iEx) {
                    this.running = false;
                }
            } else {
                delayCount++;
                if (delayCount >= 5) {
                    Thread.yield();
                    delayCount = 0;
                }
            }
        }
    }






    private void update(long l) {
        this.flipYield.update(l);
    }






    private void render() {
        Graphics2D g = this.bufferRenderer.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(this.bgImage, 0, 0, getWidth(), getHeight(), null);
        this.flipYield.render(g);
        g.dispose();
        this.bufferImage = this.bufferRenderer.getBuffer();
        repaint();
    }






    private void initRefreshRate() {
        int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        if (refreshRate == 0) {
            this.period = 32;
        } else {
            this.period = (1000 / refreshRate);
        }
        System.out.println("RENDER PANEL PERIOD VALUE SET ON: \t" + this.period);
    }




    public class BufferRenderer {

        private Dimension buffer_dim = Toolkit.getDefaultToolkit().getScreenSize();
        private BufferedImage buffer;
        private int width;
        private int height;






        public BufferRenderer(int width, int height) {
            this.width = width;
            this.height = height;
        }






        public BufferedImage getBuffer() {
            if (this.buffer == null) {
                return createBuffer();
            }
            return this.buffer;
        }






        public void setSize(int width, int height) {
            this.width = width;
            this.height = height;
        }






        public Graphics2D getGraphics() {
            if (this.buffer != null) {
                this.buffer.flush();
            }
            this.buffer = createBuffer();
            return this.buffer.createGraphics();
        }






        private BufferedImage createBuffer() {
            return new BufferedImage(this.buffer_dim.width, this.buffer_dim.height, 2);
        }
    }






    private BufferedImage loadBG() {
        BufferedImage img = null;
        URL url = getClass().getResource("/wallpaper.jpg");
        try {
            img = ImageIO.read(url);
        } catch (IOException localIOException) {
        }
        return img;
    }






    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(6);
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().setLayout(new BorderLayout());
        RenderPanel panel = new RenderPanel();
        frame.getContentPane().add(panel);
        frame.validate();
        panel.setFramePeriod(24);
        frame.setVisible(true);
        panel.startPanel();
    }

}

