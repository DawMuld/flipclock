/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;

import contracts.Animatable;
import java.awt.Graphics2D;
import java.util.Iterator;




/**
 *
 * @author daan-
 */
public class CommandProcessor implements ViewComponent {

    private final Composite composite;
    private final CommandQueue queue;






    /**
     *
     * @param composite
     */
    public CommandProcessor(Composite composite) {
        this.composite = composite;
        this.queue = new CommandQueue();
    }






    public CommandProcessor() {
        this.composite = new Composite();
        this.queue = new CommandQueue();
    }






    @Override
    public boolean isEmpty() {
        return this.composite.isEmpty();
    }






    @Override
    public boolean add(Animatable animatable) {
        this.queue.enqueue(new AddCommand(animatable));
        return true;
    }






    @Override
    public boolean remove(Animatable animatable) {
        this.queue.enqueue(new RemoveCommand(animatable));
        return true;
    }






    @Override
    public Iterator<Animatable> iterator() {
        return this.composite.iterator();
    }






    @Override
    public void clear() {
        this.queue.enqueue(new ClearCommand());
    }






    @Override
    public void update(long timeStep) {
        this.composite.updateTime(timeStep);
        if (!this.queue.isEmpty()) {
            this.queue.makeEmpty(this.composite);
        }
    }






    @Override
    public void render(Graphics2D g) {
        this.composite.render(g);
    }




    public final class AddCommand implements CompositeCommand {

        private final Animatable subject;






        public AddCommand(Animatable subject) {
            this.subject = subject;
        }






        @Override
        public void execute(ViewComponent comp) {
            comp.add(this.subject);
        }
    }




    public final class RemoveCommand implements CompositeCommand {

        private final Animatable subject;






        public RemoveCommand(Animatable subject) {
            this.subject = subject;
        }






        @Override
        public void execute(ViewComponent comp) {
            comp.remove(this.subject);
        }
    }




    public final class ClearCommand implements CompositeCommand {

        public ClearCommand() {
        }






        @Override
        public void execute(ViewComponent comp) {
            comp.clear();
        }
    }


}

