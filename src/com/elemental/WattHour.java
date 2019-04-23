/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;




/**
 *
 * @author daan-
 */
public class WattHour {

    private BufferedImage kwhImage;
    private Rectangle bounds;






    public WattHour(BufferedImage image) {
        this.kwhImage = image;
    }






    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }






    public void render(Graphics2D g) {
        g.setClip(this.bounds);
        g.drawImage(this.kwhImage, this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height, null);
    }
}

