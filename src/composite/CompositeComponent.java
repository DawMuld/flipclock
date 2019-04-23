/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import contracts.Animatable;
import java.awt.Graphics2D;
import java.util.Iterator;
import view.RenderEngine;




/**
 *
 * @author daan-
 */
public class CompositeComponent implements ViewComponent {

    private final CommandProcessor commandProcessor;
    private final RenderEngine renderEngine;






    public CompositeComponent(RenderEngine renderEngine) {
        this.commandProcessor = new CommandProcessor();
        this.renderEngine = renderEngine;
    }






    @Override
    public boolean isEmpty() {
        return this.commandProcessor.isEmpty();
    }






    @Override
    public boolean add(Animatable animatable) {
        return this.commandProcessor.add(animatable);
    }






    @Override
    public boolean remove(Animatable animatable) {
        return this.commandProcessor.remove(animatable);
    }






    @Override
    public Iterator<Animatable> iterator() {
        return this.commandProcessor.iterator();
    }






    @Override
    public void clear() {
        this.commandProcessor.clear();
    }






    @Override
    public void update(long timeStep) {
        this.commandProcessor.update(timeStep);
    }






    @Override
    public void render(Graphics2D g) {
        this.commandProcessor.render(g);
    }
}

