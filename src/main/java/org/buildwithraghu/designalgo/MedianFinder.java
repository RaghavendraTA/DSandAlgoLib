package org.buildwithraghu.designalgo;

import java.util.*;

public class MedianFinder {

    PriorityQueue<Integer> maxHeap, minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek())
            maxHeap.offer(num);
        else
            minHeap.offer(num);

        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (minHeap.size() > maxHeap.size())
            maxHeap.add(minHeap.poll());
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size())
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (!minHeap.isEmpty()) {
            minHeap.peek();
        }
        return 0.0;
    }

    public static void main(String[] args) {
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        m.addNum(3);
        System.out.println(m.maxHeap);
        System.out.println(m.minHeap);
        System.out.println(m.findMedian());
    }
}
