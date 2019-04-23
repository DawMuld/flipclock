/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import blackboard.YieldUpdate;
import contracts.EngineController;




/**
 *
 * @author daan-
 */
public class TotalEntity {

    private StateChangeReporter stateChangeReporter;
    private EngineController controller;
    private YieldUpdate yieldUpdate;
    private TotalAnimator totalAnimator;
    private int initialYieldValue;
    private boolean isUpdated = false;






    public TotalEntity(int initialYieldValue) {
        this.initialYieldValue = initialYieldValue;
        this.stateChangeReporter = new StateChangeReporter();
        this.totalAnimator = new TotalAnimator(this);
    }






    public EntityStateAccessor getStateChangeReporter() {
        return this.stateChangeReporter;
    }






    public EntityAnimator getEntityAnimator() {
        return this.totalAnimator;
    }






    public void attachEngineController(EngineController controller) {
        this.controller = controller;
    }






    public void removedFromView() {
    }






    public boolean changeBackground() {
        return true;
    }






    public void recieveYieldUpdate(YieldUpdate update) {
        this.yieldUpdate = update;
        this.isUpdated = true;
    }






    public int getYield() {
        if (this.yieldUpdate == null) {
            return this.initialYieldValue;
        }
        this.isUpdated = false;
        return this.yieldUpdate.getTotalYieldValue();
    }






    public boolean isUpdated() {
        return this.isUpdated;
    }






    void setFramePeriod(int i) {
        if (this.controller != null) {
            this.controller.setPeriod(i);
        } else {
            System.out.println("controller is null");
        }
    }






    void finishUp() {
        this.stateChangeReporter.entityFinished();
    }
}

