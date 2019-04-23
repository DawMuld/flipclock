/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.elemental.FlipYield;
import java.awt.Graphics2D;




/**
 *
 * @author daan-
 */
public class TotalAnimator implements EntityAnimator {

    private TotalEntity totalEntity;
    private long duration = 9000L;
    private long elapsedTime = 0L;
    private FlipYield flipYield;
    private boolean flipvalFinished = false;






    public TotalAnimator(TotalEntity totalEntity) {
        this.totalEntity = totalEntity;
    }






    @Override
    public void prepare() {
        if (this.flipYield == null) {
            this.flipYield = new FlipYield(this.totalEntity.getYield());
        }
        if (this.totalEntity.isUpdated()) {
            this.flipYield.constructValue(this.totalEntity.getYield());
        }
        this.flipYield.prepare();
        this.elapsedTime = 0L;
        this.flipvalFinished = false;
    }






    @Override
    public void activate() {
        this.totalEntity.setFramePeriod(22);
    }






    @Override
    public void update(long timeStep) {
        if (!this.flipvalFinished) {
            updateYieldValue();
        }
        updatePresentTimer(timeStep);
        if (this.elapsedTime >= this.duration) {
            finishUp();
        }
        if (timeStep < 20L) {
            this.totalEntity.setFramePeriod(20);
            System.out.println("set period to 30 by total entity");
        }
    }






    @Override
    public void render(Graphics2D g) {
        this.flipYield.render(g);
    }






    private void updateYieldValue() {
        this.flipYield.update(16L);
        if (this.flipYield.isFinished()) {
            this.flipvalFinished = true;
            this.totalEntity.setFramePeriod(100);
        }
    }






    private void updatePresentTimer(long timeStep) {
        this.elapsedTime += timeStep;
    }






    private void finishUp() {
        this.elapsedTime = 0L;
        this.flipvalFinished = false;
        this.totalEntity.finishUp();
    }
}

