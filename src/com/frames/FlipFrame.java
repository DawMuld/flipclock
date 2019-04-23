/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frames;

import com.image.DigitImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;




/**
 *
 * @author daan-
 */
public abstract class FlipFrame {

    public void render(Graphics2D g, Rectangle bounds, DigitImage digit) {
        renderBoard(bounds, g);
        renderDigit(digit, bounds, g);
    }






    public abstract void renderBoard(Rectangle paramRectangle, Graphics2D paramGraphics2D);






    public abstract void renderDigit(DigitImage paramDigitImage, Rectangle paramRectangle, Graphics2D paramGraphics2D);
}

