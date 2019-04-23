/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import com.frames.FlipFrame;
import com.image.DigitImage;
import com.image.ImageLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;




/**
 *
 * @author daan-
 */
public class BoardFlyweight {

    private static final int UPPER = 0;
    private static final int LOWER = 15;
    private FlipFrame[] flipFrames;
    private DigitImage[] digitImages;






    public BoardFlyweight() {
        ImageLoader loader = new ImageLoader();
        this.flipFrames = loader.loadBoardFrames();
        this.digitImages = loader.loadDigitImages();
    }






    public void renderBoardBase(Graphics2D g, Rectangle bounds) {
        this.flipFrames[0].renderBoard(bounds, g);
        this.flipFrames[15].renderBoard(bounds, g);
    }






    public void renderRigidValue(Graphics2D g, Rectangle bounds, int value) {
        this.flipFrames[0].renderDigit(this.digitImages[value], bounds, g);
        this.flipFrames[15].renderDigit(this.digitImages[value], bounds, g);
    }






    public void renderSequenceBase(Graphics2D g, Rectangle bounds, int value) {
        int index = value + 1;
        if (index >= 10) {
            index = 0;
        }
        if (value >= 10) {
            value = 0;
        }
        this.flipFrames[0].renderDigit(this.digitImages[index], bounds, g);
        this.flipFrames[15].renderDigit(this.digitImages[value], bounds, g);
    }






    public void renderFlipFrame(Graphics2D g, Rectangle bounds, int frame, int value) {
        if ((frame >= 0) && (frame <= 7)) {
            this.flipFrames[frame].renderBoard(bounds, g);
            this.flipFrames[frame].renderDigit(this.digitImages[value], bounds, g);
        } else if ((frame > 7) && (frame < 15)) {
            int index = value + 1;
            if (index == 10) {
                index = 0;
            }
            this.flipFrames[frame].renderBoard(bounds, g);
            this.flipFrames[frame].renderDigit(this.digitImages[index], bounds, g);
        } else {
            this.flipFrames[15].renderBoard(bounds, g);
            this.flipFrames[15].renderDigit(this.digitImages[value], bounds, g);
        }
    }

}

