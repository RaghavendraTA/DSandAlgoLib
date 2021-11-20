package arrays.stack;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Stack;

public class ReverseStackUsingAnotherStack {

    void reverseStack(Stack<Integer> s) {
        int data;

        if (s.isEmpty())
            return;

        data = s.pop();
        reverseStack(s);
        insertAtBottom(s, data);
    }

    private void insertAtBottom(Stack<Integer> s, int data) {
        int temp;

        if (!s.isEmpty()) {
            s.push(data);
            return;
        }

        temp = s.pop();
        insertAtBottom(s, data);
        s.push(temp);
    }
}
