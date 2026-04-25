package org.buildwithraghu.queue;

import java.util.*;

public class SubseqLenOfKWithLargestSum {

    // https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum
    public int[] maxSubsequence(int[] nums, int k) {
        int[] ans = new int[k];
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0]-x[0]);
        for(int i = 0; i < nums.length; i++) {
            pq.offer(new int[]{nums[i], i});
        }

        Arrays.fill(nums, Integer.MIN_VALUE);

        while(k > 0) {
            int[] p = pq.poll();
            nums[p[1]] = p[0];
            k--;
        }
        int pp = 0;
        for (int num : nums) {
            if (num > Integer.MIN_VALUE) {
                ans[pp++] = num;
            }
        }
        return ans;
    }
}
