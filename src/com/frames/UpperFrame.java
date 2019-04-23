/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frames;

import com.image.DigitImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;




/**
 *
 * @author daan-
 */
public class UpperFrame extends FlipFrame {

    private final BufferedImage boardImage;
    private final double heightScale;






    public UpperFrame(BufferedImage boardImage, double heightScale) {
        this.boardImage = boardImage;
        this.heightScale = heightScale;
    }






    @Override
    public void renderBoard(Rectangle bounds, Graphics2D g) {
        g.setClip(bounds.x, bounds.y, bounds.width, bounds.height / 2);
        g.drawImage(this.boardImage, bounds.x, bounds.y, bounds.width, bounds.height / 2, null);
    }






    @Override
    public void renderDigit(DigitImage digit, Rectangle bounds, Graphics2D g) {
        int height = (int) (this.heightScale * (bounds.getHeight() / 2.0D));
        int yCoord = bounds.y + bounds.height / 2 - height - 3;
        int xCoord = bounds.x + 20;
        int width = bounds.width - 40;
        g.setClip(bounds.x, bounds.y, bounds.width, bounds.height / 2);
        g.drawImage(digit.getUpperImage(), xCoord, yCoord, width, height, null);
    }

}

