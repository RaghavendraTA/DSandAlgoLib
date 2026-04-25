package org.buildwithraghu.queue;

import java.util.*;

public class FIndKPairsWithSmallestSum {

    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));
        for(int i = 0; i < nums1.length; i++) {
            pq.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while(!pq.isEmpty() && k > 0) {
            int[] t = pq.poll();
            int i = t[0], j = t[1];
            ans.add(Arrays.asList(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                pq.offer(new int[]{i, j+1, nums1[i] + nums2[j+1]});
            }
            k--;
        }
        return ans;
    }
}
