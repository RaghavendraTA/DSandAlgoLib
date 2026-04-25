package org.buildwithraghu.designalgo;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

// https://leetcode.com/problems/min-stack/
public class MinStack<T extends Comparable<T>> {

    Deque<T> stack, minStack;

    public MinStack() {
        stack = new ConcurrentLinkedDeque<>();
        minStack = new ConcurrentLinkedDeque<>();
    }

    public void push(T val) {
        stack.addLast(val);
        if (minStack.isEmpty() || val.compareTo(minStack.peekLast()) <= 0) {
            minStack.addLast(val);
        }
    }

    public void pop() {
        T p = stack.pollLast();
        if (p != null && !minStack.isEmpty() && p.compareTo(minStack.peekLast()) == 0) {
            minStack.pollLast();
        }
    }

    public T top() {
        return stack.peekLast();
    }

    public T getMin() {
        return minStack.peekLast();
    }

    public static void main(String[] args) {
        MinStack<Integer> minStack = new MinStack<>();
        minStack.push(5);
        System.out.println(minStack.getMin());
        minStack.push(4);
        System.out.println(minStack.getMin());
        minStack.push(7);
        System.out.println(minStack.getMin());
        minStack.push(2);
        System.out.println(minStack.getMin());
    }
}
