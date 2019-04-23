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
public interface EntityStateAccessor {

    public EntityState currentState();






    public StateChangeHistory stateHistory();






    public boolean isCoupled();






    public void couple(StateChangeMediator mediator);






    public StateChangeMediator decouple();

}

