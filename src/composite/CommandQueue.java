/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composite;




/**
 *
 * @author daan-
 */
class CommandQueue {

    private Node head;
    private Node tail;






    public CommandQueue() {
        this.head = (this.tail = null);
    }






    public void enqueue(CompositeCommand command) {
        Node node = new Node(command);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            node.prev = this.tail;
            node.prev.next = node;
            this.tail = node;
        }
    }






    public CompositeCommand dequeue() {
        CompositeCommand command = this.head.data;
        this.head = this.head.next;
        this.head.prev = null;
        return command;
    }






    public boolean isEmpty() {
        return this.head == null;
    }






    public void makeEmpty(ViewComponent comp) {
        while (this.head != null) {
            CompositeCommand command = this.head.data;
            this.head = this.head.next;
            command.execute(comp);
        }
    }




    public class Node {

        CompositeCommand data;
        Node next;
        Node prev;






        public Node(CompositeCommand data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }


}

