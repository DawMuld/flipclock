/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import contracts.Animatable;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;




/**
 *
 * @author daan-
 */
class Composite implements ViewComponent {

    private final List<Animatable> elements = new CopyOnWriteArrayList<>();
    private Iterator<Animatable> iterator;
    private boolean isEmpty;






    public Composite() {
        isEmpty = true;
    }






    @Override
    public boolean isEmpty() {
        return isEmpty;
    }






    @Override
    public boolean add(Animatable animatable) {
        boolean added = false;
        if (!this.elements.contains(animatable)) {
            this.elements.add(animatable);
            this.isEmpty = this.elements.isEmpty();
            added = true;
        } else {
            System.out.println("already present");
        }
        return added;
    }






    @Override
    public boolean remove(Animatable animatable) {
        boolean removed = false;
        if (!this.isEmpty) {
            this.iterator = this.elements.iterator();
            while (this.iterator.hasNext()) {
                if (animatable == this.iterator.next()) {
                    this.iterator.remove();
                    removed = true;
                }
            }
            this.isEmpty = this.elements.isEmpty();
        }
        return removed;
    }






    @Override
    public Iterator<Animatable> iterator() {
        Iterator<Animatable> iter = null;
        if (!this.isEmpty) {
            iter = this.elements.iterator();
        }
        return iter;
    }






    @Override
    public void clear() {
        this.elements.clear();
    }






    @Override
    public void update(long dt) {
        if (!this.isEmpty) {
            this.iterator = this.elements.iterator();
            while (this.iterator.hasNext()) {
                ((Animatable) this.iterator.next()).update(dt);
            }
        }
    }






    @Override
    public void render(Graphics2D g) {
        if (!this.isEmpty) {
            this.iterator = this.elements.iterator();
            while (this.iterator.hasNext()) {
                ((Animatable) this.iterator.next()).render(g);
            }
        }
    }






    void updateTime(long timeStep) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

