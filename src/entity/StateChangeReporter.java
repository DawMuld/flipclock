/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import contracts.StateChangeMediator;




/**
 *
 * @author daan-
 */
public class StateChangeReporter implements StateChangeObserver, EntityStateAccessor {

    private StateChangeHistory stateHistory;
    private StateChangeMediator mediator;
    private EntityState currentState;
    private boolean mediatorCoupled;






    public StateChangeReporter() {
        this.stateHistory = new StateChangeHistory(7);
        this.mediator = null;
        this.currentState = null;
        this.mediatorCoupled = false;
    }






    public StateChangeReporter(StateChangeMediator mediator) {
        this.stateHistory = new StateChangeHistory(7);
        this.mediator = mediator;
        this.currentState = null;
        this.mediatorCoupled = (mediator != null);
    }






    public void entityCreated() {
        this.currentState = EntityState.CREATED;
        this.stateHistory.put(this.currentState);
        if (this.mediatorCoupled) {
            this.mediator.stateChanged(this.currentState);
        }
    }






    public void entityPrepared() {
        this.currentState = EntityState.PREPARED;
        this.stateHistory.put(this.currentState);
        if (this.mediatorCoupled) {
            this.mediator.stateChanged(this.currentState);
        }
    }






    public void entityPresenting() {
        this.currentState = EntityState.PRESENTING;
        this.stateHistory.put(this.currentState);
        if (this.mediatorCoupled) {
            this.mediator.stateChanged(this.currentState);
        }
    }






    public void entityFinished() {
        this.currentState = EntityState.FINISHED;
        this.stateHistory.put(this.currentState);
        if (this.mediatorCoupled) {
            this.mediator.stateChanged(this.currentState);
        }
    }






    public void entityInactivated() {
        this.currentState = EntityState.INACTIVE;
        this.stateHistory.put(this.currentState);
        if (this.mediatorCoupled) {
            this.mediator.stateChanged(this.currentState);
        }
    }






    public void entityIdle() {
        this.currentState = EntityState.IDLE;
        this.stateHistory.put(this.currentState);
        if (this.mediatorCoupled) {
            this.mediator.stateChanged(this.currentState);
        }
    }






    @Override
    public boolean isCoupled() {
        return this.mediatorCoupled;
    }






    @Override
    public void couple(StateChangeMediator mediator) {
        this.mediator = mediator;
        this.mediatorCoupled = (mediator != null);
    }






    @Override
    public StateChangeMediator decouple() {
        StateChangeMediator hold = this.mediator;
        this.mediator = null;
        this.mediatorCoupled = false;
        return hold;
    }






    @Override
    public EntityState currentState() {
        return this.currentState;
    }






    @Override
    public StateChangeHistory stateHistory() {
        return this.stateHistory;
    }






    @Override
    public void stateChanged(EntityState state) {
        this.mediator.stateChanged(state);
    }

}

