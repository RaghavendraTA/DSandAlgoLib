package arrays.queues;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianInInfiniteSeries {

    public static double medianUsingHeaps(int[] A) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (final int j : A) {
            if (!minHeap.isEmpty() && j < minHeap.peek()) {
                maxHeap.add(j);
                if (maxHeap.size() > minHeap.size() + 1) {
                    minHeap.add(maxHeap.poll());
                }
            }
            else {
                minHeap.add(j);
                if (minHeap.size() > maxHeap.size() + 1) {
                    maxHeap.add(minHeap.poll());
                }
            }
        }

        if (maxHeap.size() > 0 && maxHeap.size() == minHeap.size()) {
            return ((double)(minHeap.peek() + maxHeap.peek())) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else if (maxHeap.size() > 0){
           return maxHeap.peek();
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println(medianUsingHeaps(new int[]{1, 9, 2, 0, 10, 11, 12}));
    }
}
