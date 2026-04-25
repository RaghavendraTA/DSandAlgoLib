package arrays.queues;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SuccessivePairIsInStackIsConsecutive {

    static int checkStackPairwiseOrder(Stack<Integer> stack) {

        Deque<Integer> queue = new LinkedList<>();

        int pairWiseOrder = 1;

        while(!stack.isEmpty()) {
            queue.addLast(stack.pop());
        }

        while(!queue.isEmpty()) {
            stack.push(queue.pollFirst());
        }

        while(!stack.isEmpty()) {

            int n = stack.pop();
            queue.addLast(n);

            if (!stack.isEmpty()) {
                int m = stack.pop();
                queue.addLast(m);
                if (Math.abs(n-m) != 1) {
                    pairWiseOrder = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            stack.push(queue.pollFirst());
        }

        return pairWiseOrder;
    }
}
