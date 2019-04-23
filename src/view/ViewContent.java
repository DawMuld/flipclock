/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import composite.CompositeComponent;
import contracts.Animatable;
import java.awt.Graphics2D;




/**
 *
 * @author daan-
 */
public class ViewContent implements Animatable {

    private final CompositeComponent contentView;
    private final CompositeComponent overlayView;






    public ViewContent(RenderEngine engine) {
        this.contentView = new CompositeComponent(engine);
        this.overlayView = new CompositeComponent(engine);
    }






    @Override
    public void update(long timeStep) {
        this.contentView.update(timeStep);
        this.overlayView.update(timeStep);
    }






    @Override
    public void render(Graphics2D g) {
        this.contentView.render(g);
        this.overlayView.render(g);
    }






    public CompositeComponent getContentView() {
        return this.contentView;
    }






    public CompositeComponent getOverlayView() {
        return this.overlayView;
    }
}

