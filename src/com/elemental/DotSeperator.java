/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;




/**
 *
 * @author daan-
 */
public class DotSeperator {

    private BufferedImage dotImage;
    private Rectangle[] dotBounds;






    public DotSeperator(BufferedImage dotImage) {
        this.dotImage = dotImage;
        this.dotBounds = null;
    }






    public void setDotBounds(Rectangle[] dotBounds) {
        this.dotBounds = dotBounds;
    }






    public void render(Graphics2D g) {
        if (this.dotBounds != null) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Rectangle bound : this.dotBounds) {
                g.setClip(bound);
                g.drawImage(this.dotImage, bound.x + 4, bound.y + 4, bound.width - 8, bound.height - 8, null);
            }
        }
    }

}

