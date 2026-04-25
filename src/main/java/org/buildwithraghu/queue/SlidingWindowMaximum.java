package org.buildwithraghu.queue;

import java.util.*;

public class SlidingWindowMaximum {

    static class Pair {
        int val;
        int idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length-k+1];
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.val - x.val);
        int p = 0;
        for(int i = 0; i < nums.length; i++) {
            pq.offer(new Pair(nums[i], i));
            if (i >= k - 1) {
                while(pq.peek().idx <= i - k) {
                    pq.poll();
                }
                ans[p++] = pq.peek().val;
            }
        }
        return ans;
    }

    // Monotonic queue - bit faster
    public int[] maxSlidingWindow_deque(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        res.add(nums[dq.peekFirst()]);

        for (int i = k; i < nums.length; i++) {
            if (dq.peekFirst() == i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(i);
            res.add(nums[dq.peekFirst()]);
        }
        // Return the result as an array.
        return res.stream().mapToInt(i->i).toArray();
    }
}
