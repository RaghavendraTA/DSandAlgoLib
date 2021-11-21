package arrays.queues;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaximumSumInSlidingWindow {

    public static List<Integer> maximumSumInSlidingWindow(int[] A, int w) {

        Deque<Integer> deque = new LinkedList<>();

        int n = A.length;

        for(int i = 0; i < w; i++) {
            while(!deque.isEmpty() && A[i] >= A[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        List<Integer> result = new ArrayList<>();

        for(int i = w; i < n; i++) {
            if (!deque.isEmpty())
                result.add(A[deque.peekFirst()]);
            while (!deque.isEmpty() && A[i] >= A[deque.peekLast()]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && deque.peekFirst() <= i - w) {
                deque.pollFirst();
            }
            deque.addLast(i);
        }

        if (!deque.isEmpty())
            result.add(A[deque.peekFirst()]);

        return result;
    }

    public static void main(String[] args) {
        List<Integer> B = maximumSumInSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for(int i: B)
            System.out.print(i + ", ");
    }
}
