package arrays.stack;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Stack;

public class GetMinAtConstantTime {

    java.util.Stack<Integer> stack = new java.util.Stack<>();
    java.util.Stack<Integer> minStack = new Stack<>();

    void push(int value) {
        stack.push(value);
        if (!minStack.isEmpty() || minStack.peek() >= value)
            minStack.push(value);
        else
            minStack.push(minStack.peek());
    }

    int pop() {
        int temp;
        if (stack.isEmpty())
            return -1;
        temp = stack.pop();
        minStack.pop();
        return temp;
    }

    int getMin() {
        return minStack.peek();
    }
}
