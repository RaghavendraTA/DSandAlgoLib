package org.buildwithraghu.queue;

import java.util.*;

public class TopKFreq {

    // https://leetcode.com/problems/top-k-frequent-elements
    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }

        // 1. Build hash map: character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.merge(n, 1, Integer::sum);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(count::get));

        // 2. Keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. Build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
}
