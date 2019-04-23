/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import contracts.Animatable;
import contracts.EngineController;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;




/**
 *
 * @author daan-
 */
public class RenderEngine extends JPanel implements Runnable, EngineController {

    private static final int PERIOD = 20;
    private final ViewContent viewContent;
    private final Dimension viewSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final RenderBuffer renderBuffer = new RenderBuffer();
    private Thread animator;
    private volatile boolean isRunning;
    private volatile boolean isPaused;
    private volatile int period;
    private BufferedImage offScreenBuffer;
    private Animatable animatable;






    public RenderEngine() {
        super(null);
        this.viewContent = new ViewContent(this);
        initializeEngineSettigns();
    }






    public RenderEngine(ViewContent viewContent) {
        super(null);
        this.viewContent = viewContent;
        initializeEngineSettigns();
    }






    @Override
    public ViewContent getViewContent() {
        return this.viewContent;
    }






    public boolean isPaused() {
        return (this.isRunning) && (this.isPaused);
    }






    public boolean isRunning() {
        return (this.isRunning) && (!this.isPaused);
    }






    @Override
    public void setPeriod(int period) {
        this.period = period;
    }






    @Override
    public void pause() {
        this.isPaused = true;
    }






    @Override
    public void resume() {
        this.isPaused = false;
    }






    @Override
    public void terminate() {
        this.isPaused = false;
        this.isRunning = false;
    }






    @Override
    public void run() {
        this.isRunning = true;
        this.isPaused = false;
        requestFocusInWindow();
        loopRender();
    }






    private void loopRender() {
        long startTime = System.nanoTime();
        long currentTime = startTime;
        long sleepTime;
        int delayCount = 0;
        this.isPaused = false;
        while (this.isRunning) {
            long elapsedTime = System.nanoTime() - currentTime;
            currentTime += elapsedTime;
            update(elapsedTime / 1000000L);
            render();
            long deltaTime = System.nanoTime() - currentTime;
            sleepTime = this.period - deltaTime / 1000000L;
            if (sleepTime > 0L) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException iEx) {
                    System.out.println("interrupted engine");
                }
            } else {
                delayCount++;
                if (delayCount >= 5) {
                    Thread.yield();
                    delayCount = 0;
                }
            }
            if ((this.isPaused) && (this.isRunning)) {
                System.out.println("Paused");
                int pauseCount = 0;
                while (this.isPaused) {
                    if (pauseCount > 6) {
                        this.isPaused = false;
                    }
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        this.isPaused = false;
                        System.out.println("pause exception thrown");
                    }
                    pauseCount++;
                    currentTime = System.nanoTime();
                }
                System.out.println("resume render engine main loop");
            }
        }
    }






    private void update(long l) {
        this.viewContent.update(l);
    }






    private void render() {
        Graphics2D g = this.renderBuffer.getBufferGraphics();
        this.viewContent.render(g);
        g.dispose();
        this.offScreenBuffer = this.renderBuffer.getFrameBuffer();
        repaint();
    }






    public void startPanel() {
        if ((this.animator == null) || (!this.animator.isAlive())) {
            this.animator = new Thread(this);
            this.animator.start();
        }
    }






    public void setAnimatable(Animatable animatable) {
        this.animatable = animatable;
    }






    @Override
    public void paint(Graphics g) {
        if (this.offScreenBuffer != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(this.offScreenBuffer, 0, 0, this);
            g2d.dispose();
            g.dispose();
        } else {
            System.out.println("null buffer");
        }
    }






    private void initializeEngineSettigns() {
        setPreferredSize(this.viewSize);
        setSize(this.viewSize);
        setOpaque(false);
        int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        if (refreshRate == 0) {
            this.period = 20;
            System.out.println("period = PERIOD; REFRESH_RATE_UNKNOWN");
        } else {
            this.period = (1000 / refreshRate);
            System.out.println("period = " + this.period + " 1000 / refreshRate");
        }
    }






    public void setPeriod() {
        int refreshRate = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getRefreshRate();
        if (refreshRate == 0) {
            this.period = 20;
        } else {
            this.period = (2 * (1000 / refreshRate));
        }
        if (this.period >= 25) {
            this.period = 20;
        }
        System.out.println("period set to default: " + this.period);
    }

}

