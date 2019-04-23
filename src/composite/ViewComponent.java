/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import contracts.Animatable;
import java.util.Iterator;




/**
 *
 * @author daan-
 */
public interface ViewComponent extends Animatable {

    public boolean isEmpty();






    public boolean add(Animatable animatable);






    public boolean remove(Animatable animatable);






    public Iterator<Animatable> iterator();






    public void clear();

}

