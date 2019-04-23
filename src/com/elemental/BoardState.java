/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import java.awt.Rectangle;




/**
 *
 * @author daan-
 */
public class BoardState {

    public static long t_anim = 2560L;
    private static long t_s = 256L;
    private static long t_f = 16L;
    private int endValue;
    private Rectangle bounds;
    private int s;
    private long d_i;
    private long d_vs;






    public BoardState(int endValue, Rectangle bounds) {
        this.endValue = endValue;
        this.bounds = bounds;
        this.s = getS(endValue);
        this.d_i = getIntervalDuration(this.s);
        this.d_vs = (this.d_i + t_s);
    }






    public BoardState(Rectangle bounds) {
        this.bounds = bounds;
    }






    public void setEndValue(int endValue) {
        this.endValue = endValue;
        this.s = getS(endValue);
        this.d_i = getIntervalDuration(this.s);
        this.d_vs = (this.d_i + t_s);
    }






    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }






    public boolean isInSequece(long t) {
        long tCorr = t % this.d_vs;
        return tCorr >= this.d_i;
    }






    public int getValue(long t) {
        long var1 = t - t % this.d_vs;
        if (var1 <= 0L) {
            return 0;
        }
        return (int) (var1 / this.d_vs);
    }






    public int getFrame(long t) {
        long tCorrect = t % this.d_vs;
        if (tCorrect < this.d_i) {
            return 0;
        }
        tCorrect -= this.d_i;
        return (int) ((tCorrect - tCorrect % t_f) / t_f);
    }






    public Rectangle getBounds() {
        return this.bounds;
    }






    public static int getS(int endValue) {
        if (endValue == 0) {
            return 10;
        }
        return endValue;
    }






    public static long getIntervalDuration(int s) {
        if (s == 10) {
            return 0L;
        }
        return (t_anim - s * t_s) / (s + 1);
    }






    public static final void main(String[] args) {
        BoardState boardState = new BoardState(8, new Rectangle(0, 0, 200, 320));
        long time = 0L;
        while (time < 2570L) {
            System.out.println("t = " + time + "\tvalue = " + boardState.getValue(time) + "\tframe = " + boardState.getFrame(time));
            try {
                Thread.sleep(16L);
            } catch (InterruptedException localInterruptedException) {
            }
            time += 16L;
        }
    }

}

