/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;




/**
 *
 * @author daan-
 */
public class StateChangeHistory {

    private final EntityState[] queue;
    private int index;
    private int end;






    public StateChangeHistory(int size) {
        this.queue = new EntityState[size];
        this.end = (size - 1);
        this.index = 0;
    }






    public void put(EntityState state) {
        if (this.index == this.end) {
            for (int i = 0; i < this.end; i++) {
                this.queue[i] = this.queue[(i + 1)];
            }
            this.queue[this.end] = state;
        } else {
            this.queue[this.index] = state;
            this.index += 1;
        }
    }






    public EntityState get(int stepsBack) {
        EntityState retval = null;
        if (stepsBack <= this.end) {
            int i = this.end - stepsBack;
            retval = this.queue[i];
        }
        return retval;
    }






    public EntityState[] get() {
        EntityState[] retval = null;
        if (this.index > 0) {
            retval = new EntityState[this.index + 1];
            for (int i = 0; i <= this.index; i++) {
                retval[i] = this.queue[(this.index - i)];
            }
        }
        return retval;
    }

}

