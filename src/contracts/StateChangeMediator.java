/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contracts;

import entity.StateChangeObserver;




/**
 *
 * @author daan-
 */
public abstract class StateChangeMediator implements StateChangeObserver {

    public abstract void entityCreated();






    public abstract void entityPrepared();






    public abstract void entityPresenting();






    public abstract void entityFinished();






    public abstract void entityInactivated();






    public abstract void entityIdle();

}

