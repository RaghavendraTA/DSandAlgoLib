package org.buildwithraghu.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxSlidingWindow {

    static class Pair {
        int val;
        int idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int p = 0;
        int[] ans = new int[nums.length-k+1];
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.val - x.val);

        for(int i = 0; i < nums.length; i++) {
            pq.add(new Pair(nums[i], i));
            if (i >= k - 1) {
                while(pq.peek().idx <= i-k) {
                    pq.poll();
                }
                ans[p++] = pq.peek().val;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9,10,9,-7,-4,-8,2,-6};
        int k = 5;
        MaxSlidingWindow mw = new MaxSlidingWindow();
        System.out.println(Arrays.toString(mw.maxSlidingWindow(nums, k)));
    }
}
