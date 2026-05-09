package org.buildwithraghu.queue;

import java.util.*;

class FrontMiddleBackQueue {

    Deque<Integer> left;
    Deque<Integer> right;

    public FrontMiddleBackQueue() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    private void balance() {
        if (left.size() > right.size())
            right.addFirst(left.removeLast());
        else if (right.size() > left.size() + 1)
            left.addLast(right.removeFirst());
    }

    public void pushFront(int val) {
        left.addFirst(val);
        balance();
    }

    public void pushMiddle(int val) {
        if (left.size() < right.size())
            left.addLast(val);
        else
            right.addFirst(val);
        balance();
    }

    public void pushBack(int val) {
        right.addLast(val);
        balance();
    }

    public int popFront() {
        if (isEmpty()) return -1;
        int val = left.isEmpty() ? right.removeFirst() : left.removeFirst();
        balance();
        return val;
    }

    public int popMiddle() {
        if (isEmpty()) return -1;
        int val;
        if (left.size() == right.size()) // If even, take from left
            val = left.removeLast();
        else // If odd, right has the middle element
            val = right.removeFirst();
        balance();
        return val;
    }

    public int popBack() {
        if (isEmpty()) return -1;
        int val = right.removeLast();
        balance();
        return val;
    }

    private boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }
}