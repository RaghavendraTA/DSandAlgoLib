package arrays.queues;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * Steps to achieve:
 *
 * 1.Push the first half elements of queue to stack.
 * 2.Enqueue back the stack elements.
 * 3.Dequeue the first half elements of the queue and enqueue them back.
 * 4.Again push the first half elements into the stack.
 * 5.Interleave the elements of queue and stack.
 */
public class InterLeavingQueue {

    public static void interLeavingQueue(Queue<Integer> queue) {
        if (queue.size() % 2 != 0)
            return;

        Stack<Integer> stack = new Stack<>();

        int halfSize = queue.size() / 2;

        // Queue : front -> 16 17 18 19 20 <- end
        // Stack :          11 12 13 14 15 <- top
        // Push half of the queue to stack
        for(int i = 0; i < halfSize; i++) {
            stack.push(queue.poll());
        }

        // Queue : front -> 16, 17, 18, 19, 20, 15, 14, 13, 12, 11 <- end
        // Stack :          <- top
        // pop everything from the stack and append to the queue
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // Queue : front -> 15, 14, 13, 12, 11, 16, 17, 18, 19, 20 <- end
        // Stack :           <- top
        // take each value from front of the queue and append to the same again queue
        for(int i = 0; i < halfSize; i++) {
            queue.add(queue.poll());
        }

        // Queue : front -> 16, 17, 18, 19, 20, <- end
        // Stack :          15, 14, 13, 12, 11, <- top
        // Again push half of the queue values to the stack
        for(int i = 0; i < halfSize; i++) {
            stack.push(queue.poll());
        }

        // After this loop queue will look like
        // Queue : front -> 11, 16, 12, 17, 13, 18, 14, 19, 15, 20 <- end
        // Now finally print stack.top() once and queue.front() once. it will print in interLeaving
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
            queue.add(queue.poll());
        }

        System.out.println(queue);
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 11; i <= 20; i++) {
            queue.add(i);
        }
        interLeavingQueue(queue);
    }
}
