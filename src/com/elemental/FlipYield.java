/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elemental;

import com.image.ImageLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;




/**
 *
 * @author daan-
 */
public class FlipYield {

    private int flipValue;
    private int[] boardValues;
    private BoardState[] boardStates;
    private BoardFlyweight flyweight;
    private DotSeperator seperator;
    private WattHour wattHour;
    private long elapsedTime;
    private boolean isActive;






    public FlipYield(int initialValue) {
        this.flyweight = new BoardFlyweight();
        ImageLoader loader = new ImageLoader();
        this.wattHour = loader.loadWattHour();
        this.seperator = loader.loadDotSeperator();
        this.flipValue = initialValue;
        constructValue(this.flipValue);
        this.elapsedTime = 0L;
        this.isActive = true;
    }






    public boolean isFinished() {
        return this.elapsedTime >= BoardState.t_anim;
    }






    public void prepare() {
        this.elapsedTime = 0L;
        this.isActive = true;
    }






    public void update(long t) {
        if (!this.isActive) {
            return;
        }
        this.elapsedTime += t;
        if (this.elapsedTime >= 2560L) {
            this.elapsedTime = 2559L;
            this.isActive = false;
        }
    }






    public void render(Graphics2D g) {
        if (this.elapsedTime >= BoardState.t_anim) {
            this.elapsedTime = (BoardState.t_anim - 1L);
        }
        for (BoardState state : this.boardStates) {
            int value = state.getValue(this.elapsedTime);
            if (value == 10) {
                value = 0;
            }
            this.flyweight.renderBoardBase(g, state.getBounds());
            if (state.isInSequece(this.elapsedTime)) {
                int frame = state.getFrame(this.elapsedTime);
                if (frame == 15) {
                    this.flyweight.renderRigidValue(g, state.getBounds(), (value + 1) % 10);
                    this.flyweight.renderFlipFrame(g, state.getBounds(), frame, (value + 1) % 10);
                } else {
                    this.flyweight.renderSequenceBase(g, state.getBounds(), value);
                    this.flyweight.renderFlipFrame(g, state.getBounds(), frame, value);
                }
            } else {
                this.flyweight.renderRigidValue(g, state.getBounds(), value);
            }
        }
        this.seperator.render(g);
        this.wattHour.render(g);
    }






    public void constructValue(int value) {
        LayoutBuilder layoutBuilder = new LayoutBuilder(value);
        Rectangle[] boardBounds = layoutBuilder.getBoardBounds();
        this.boardValues = LayoutBuilder.stripVal(value);
        this.boardStates = new BoardState[boardBounds.length];
        for (int i = 0; i < boardBounds.length; i++) {
            BoardState state = new BoardState(this.boardValues[i], boardBounds[i]);
            this.boardStates[i] = state;
        }
        this.wattHour.setBounds(layoutBuilder.getSymbolBounds());
        this.seperator.setDotBounds(layoutBuilder.getDotBounds());
    }
}

