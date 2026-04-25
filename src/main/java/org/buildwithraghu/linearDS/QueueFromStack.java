package org.buildwithraghu.linearDS;

import org.buildwithraghu.javafeatures.interfaces.ISequence;

import java.util.Stack;

public class QueueFromStack<T extends Comparable<T>> implements ISequence<T> {

    private Stack<T> stk1 = new Stack<>(), stk2 = new Stack<>();

    @Override
    public void push(T E) {
        while(!stk1.empty()) {
            stk2.push(stk1.peek());
            stk1.pop();
        }
        stk1.push(E);
        while(!stk2.empty()) {
            stk1.push(stk2.peek());
            stk2.pop();
        }
    }

    @Override
    public T peek() throws IllegalStateException {
        if (stk1.empty()) {
            throw new IllegalStateException("Queue is Empty");
        }
        return stk1.peek();
    }

    public T front() throws IllegalStateException {
        if (stk1.empty()) {
            throw new IllegalStateException("Queue is Empty");
        }
        return stk1.peek();
    }

    @Override
    public void pop() throws IllegalStateException {
        if (stk1.empty()) {
            throw new IllegalStateException("Queue is Empty");
        }
        stk1.pop();
    }

    @Override
    public boolean isEmpty() {
        return stk1.isEmpty();
    }

    @Override
    public String toString() {
        return stk1.toString();
    }
}
