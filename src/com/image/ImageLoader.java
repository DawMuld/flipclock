/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.image;

import com.elemental.DotSeperator;
import com.elemental.WattHour;
import com.frames.FlipFrame;
import com.frames.LowerFrame;
import com.frames.UpperFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;




/**
 *
 * @author daan-
 */
public class ImageLoader {

    private static final double[] UPPER_SCALES = {0.92D, 0.88D, 0.82D, 0.75D, 0.65D, 0.45D, 0.25D, 0.15D};
    private static final double[] LOWER_SCALES = {0.15D, 0.25D, 0.45D, 0.65D, 0.75D, 0.82D, 0.88D, 0.92D};
    private String digitBase = "/WHITE_";
    private String boardBase = "/flip_";






    public DigitImage[] loadDigitImages() {
        DigitImage[] images = new DigitImage[10];
        for (int i = 0; i < 10; i++) {
            BufferedImage image = null;
            URL url = getClass().getResource(this.digitBase + String.valueOf(i) + ".png");
            try {
                image = ImageIO.read(url);
            } catch (IOException localIOException) {
            }
            DigitImage digit = new DigitImage(image, i);
            images[i] = digit;
        }
        return images;
    }






    public FlipFrame[] loadBoardFrames() {
        FlipFrame[] frames = new FlipFrame[16];
        for (int i = 0; i < 16; i++) {
            BufferedImage boardImage = null;
            URL url = getClass().getResource(this.boardBase + String.valueOf(i) + ".png");
            try {
                boardImage = ImageIO.read(url);
            } catch (IOException localIOException) {
            }
            if (i <= 7) {
                int index = i;
                FlipFrame frame = new UpperFrame(boardImage, UPPER_SCALES[index]);
                frames[i] = frame;
            } else if (i >= 8) {
                int index = i - 8;
                FlipFrame frame = new LowerFrame(boardImage, LOWER_SCALES[index]);
                frames[i] = frame;
            }
        }
        return frames;
    }






    public DotSeperator loadDotSeperator() {
        BufferedImage img = null;
        URL url = getClass().getResource("/dotcell.gif");
        try {
            img = ImageIO.read(url);
        } catch (IOException localIOException) {
        }
        return new DotSeperator(img);
    }






    public WattHour loadWattHour() {
        BufferedImage img = null;
        URL url = getClass().getResource("/kwh.png");
        try {
            img = ImageIO.read(url);
        } catch (IOException localIOException) {
        }
        return new WattHour(img);
    }

}

