package org.buildwithraghu.designalgo;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/implement-stack-using-queues
public class MyStack {

    Queue<Integer> qu1, qu2;
    public MyStack() {
        qu1 = new LinkedList<>();
        qu2 = new LinkedList<>();
    }

    public void push(int x) {
        qu2.add(x);
        while(!qu1.isEmpty()) {
            qu2.add(qu1.poll());
        }
        Queue<Integer> temp = qu1;
        qu1 = qu2;
        qu2 = temp;
    }

    public int pop() {
        return qu1.poll();
    }

    public int top() {
        return qu1.peek();
    }

    public boolean empty() {
        return qu1.isEmpty();
    }
}
