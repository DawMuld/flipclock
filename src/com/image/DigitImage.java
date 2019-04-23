/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.image;

import java.awt.image.BufferedImage;




/**
 *
 * @author daan-
 */
public class DigitImage {

    private int digitValue;
    private BufferedImage upperImage;
    private BufferedImage lowerImage;






    public DigitImage(BufferedImage digitImage, int digitValue) {
        setUpperImage(digitImage);
        setLowerImage(digitImage);
        this.digitValue = digitValue;
    }






    public BufferedImage getUpperImage() {
        return this.upperImage;
    }






    public BufferedImage getLowerImage() {
        return this.lowerImage;
    }






    public int getDigitValue() {
        return this.digitValue;
    }






    private void setUpperImage(BufferedImage digitImage) {
        int width = digitImage.getWidth();
        int height = digitImage.getHeight();
        this.upperImage = digitImage.getSubimage(0, 0, width, height / 2);
    }






    private void setLowerImage(BufferedImage digitImage) {
        int width = digitImage.getWidth();
        int height = digitImage.getHeight();
        this.lowerImage = digitImage.getSubimage(0, height / 2, width, height / 2);
    }
}

