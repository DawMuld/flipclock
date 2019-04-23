/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;




/**
 *
 * @author daan-
 */
public class LayoutBuilder {

    public static final int BRD_WEIGHT = 20;
    public static final int DOT_WEIGHT = 4;
    public static final int SYM_WEIGHT = 42;
    public static final Dimension DEF_BOARD_DIM = new Dimension(280, 410);
    private int boardCount;
    private int dotCount;
    private int coreX;
    private int coreY;
    private int coreW;
    private int coreH;
    private Dimension boardDim;
    private Dimension dotDim;
    private Dimension symbolDim;
    private Rectangle[] boardBounds;
    private Rectangle[] dotBounds;
    private Rectangle symbolBounds;






    public static Rectangle defaultPanelRect() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        double dW = screen.getWidth();
        double dH = screen.getHeight();
        double pW = 0.95D * dW;
        double pH = 0.9D * (dH / 2.0D);
        int w = (int) pW;
        int h = (int) pH;
        int x = (screen.width - w) / 2;
        int y = (screen.height / 2 - h) / 2;
        return new Rectangle(x, y, w, h);
    }






    public static int[] stripVal(int value) {
        String[] str = String.valueOf(value).split("(?!^)");
        int[] ret = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            ret[i] = Integer.parseInt(str[i]);
        }
        return ret;
    }






    public LayoutBuilder(int value) {
        this.boardCount = stripVal(value).length;
        int n = this.boardCount - 1;
        this.dotCount = ((n - n % 3) / 3);
        Rectangle core = defaultPanelRect();
        this.coreX = core.x;
        this.coreY = core.y;
        this.coreW = core.width;
        this.coreH = core.height;
        setDimensions();
        createRectangles();
    }






    public Rectangle[] getBoardBounds() {
        return this.boardBounds;
    }






    public Rectangle[] getDotBounds() {
        return this.dotBounds;
    }






    public Rectangle getSymbolBounds() {
        return this.symbolBounds;
    }






    private void setDimensions() {
        double refWidth = this.boardCount * 20 + this.dotCount * 4 + 42;
        double scaleFactor = this.coreW / refWidth;
        double bW = scaleFactor * 20.0D;
        double dW = scaleFactor * 4.0D;
        double sW = scaleFactor * 42.0D;
        double bH = DEF_BOARD_DIM.getHeight() * bW / DEF_BOARD_DIM.getWidth();
        this.boardDim = new Dimension((int) bW, (int) bH);
        this.dotDim = new Dimension((int) dW, (int) dW);
        this.symbolDim = new Dimension((int) sW, (int) bH);
    }






    private Rectangle[] createBoards(int x, int y, int w, int h, int count) {
        Rectangle[] rects = new Rectangle[count];
        int xCoord = x;
        int yCoord = y;
        for (int i = 0; i < count; i++) {
            Rectangle rect = new Rectangle(xCoord, yCoord, w, h);
            xCoord += w;
            rects[i] = rect;
        }
        return rects;
    }






    private Rectangle createDot(int x, int y, int w, int h) {
        int yCoord = y + h - w;
        return new Rectangle(x, yCoord, w, w);
    }






    private void createRectangles() {
        ArrayList<Rectangle> boards = new ArrayList();
        ArrayList<Rectangle> dots = new ArrayList();

        int primeGroup = this.boardCount % 3;
        int groups = (this.boardCount - primeGroup) / 3;
        int xCoord = this.coreX;
        int yCoord = this.coreY + this.coreH / 2 - this.boardDim.height / 2;
        if (primeGroup == 0) {
            groups--;
            primeGroup = 3;
        }
        Rectangle[] primes = createBoards(xCoord, yCoord, this.boardDim.width, this.boardDim.height, primeGroup);
        for (Rectangle prime : primes) {
            boards.add(prime);
            xCoord += prime.width;
        }
        for (int i = 0; i < groups; i++) {
            dots.add(createDot(xCoord, yCoord, this.dotDim.width, this.boardDim.height));
            xCoord += this.dotDim.width;
            Rectangle[] brects = createBoards(xCoord, yCoord, this.boardDim.width, this.boardDim.height, 3);
            xCoord += 3 * this.boardDim.width;
            Rectangle[] arrayOfRectangle2 = brects;
            int prime = arrayOfRectangle2.length;
            for (int j = 0; j < prime; j++) {
                Rectangle brect = arrayOfRectangle2[j];
                boards.add(brect);
            }
        }
        Rectangle[] brdRects = new Rectangle[this.boardCount];
        Rectangle[] dotRects = new Rectangle[this.dotCount];
        this.symbolBounds = new Rectangle(xCoord, yCoord, this.symbolDim.width, this.boardDim.height);
        for (int i = 0; i < this.boardCount; i++) {
            brdRects[i] = ((Rectangle) boards.get(i));
        }
        for (int i = 0; i < this.dotCount; i++) {
            dotRects[i] = ((Rectangle) dots.get(i));
        }
        this.boardBounds = new Rectangle[this.boardCount];
        this.dotBounds = new Rectangle[this.dotCount];
        for (int i = 0; i < this.boardCount; i++) {
            this.boardBounds[i] = ((Rectangle) boards.get(i));
        }
        for (int i = 0; i < this.dotCount; i++) {
            this.dotBounds[i] = ((Rectangle) dots.get(i));
        }
    }

}

