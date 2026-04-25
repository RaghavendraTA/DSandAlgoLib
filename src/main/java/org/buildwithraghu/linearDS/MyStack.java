package org.buildwithraghu.linearDS;

import org.buildwithraghu.javafeatures.interfaces.ISequence;

/**
 * Represents a Custom stack implemented using LinkedList.
 *
 * @author Raghavendra
 * @version 1.0
 */
public class MyStack<T extends Comparable<T>> implements ISequence<T> {

    static class Node<T> {
        T val;
        Node<T> next = null;
        Node(T E) { this.val = E; }
    }

    private Node<T> root = null;

    /**
     * Inserts the element into top of the stack
     * @param E
     */
    @Override
    public void push(T E) {
        Node<T> t = new Node<>(E);
        if (root == null) {
            root = t;
            return;
        }
        t.next = root;
        root = t;
    }

    @Override
    public T peek() throws IllegalStateException {
        if (root == null) {
            throw new IllegalStateException("Stack is Empty");
        }
        return root.val;
    }

    @Override
    public void pop() throws IllegalStateException {
        if (root == null) {
            throw new IllegalStateException("Stack is Empty");
        }
        Node<T> t = root;
        root = root.next;
        t.next = null;
        t = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public static void main(String[] args) {
        // Testing MyStack
        MyStack<Integer> stk = new MyStack<>();
        for(int i = 1; i <= 5; i++) {
            stk.push(i);
        }
        while(!stk.isEmpty()) {
            System.out.println(stk.peek());
            stk.pop();
        }
    }
}
