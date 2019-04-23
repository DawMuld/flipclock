/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;




/**
 *
 * @author daan-
 */
class RenderBuffer {

    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private GraphicsConfiguration graphicsConfig;
    private BufferedImage emptyBuffer;
    private BufferedImage frameBuffer;






    public RenderBuffer() {
        this.graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        this.emptyBuffer = emptyBuffer();
        this.frameBuffer = emptyBuffer();
    }






    public Graphics2D getBufferGraphics() {
        this.frameBuffer = this.emptyBuffer;
        this.emptyBuffer = emptyBuffer();
        return this.frameBuffer.createGraphics();
    }






    public BufferedImage getFrameBuffer() {
        return this.frameBuffer;
    }






    private BufferedImage emptyBuffer() {
        return this.graphicsConfig.createCompatibleImage(SCREEN_SIZE.width, SCREEN_SIZE.height, 3);
    }
}

