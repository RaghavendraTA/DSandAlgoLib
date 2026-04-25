package org.buildwithraghu.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubseq {

    // https://leetcode.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS(int[] nums) {
        List<Integer> set = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int idx = Collections.binarySearch(set, nums[i]);
            System.out.println(nums[i] + " -> " + idx);
            if (idx < 0)
                idx = -(idx + 1);
            if (idx == set.size())
                set.add(nums[i]);
            else
                set.set(idx, nums[i]);
        }
        return set.size();
    }

    public static void main(String[] args) {
        new LongestIncreasingSubseq().lengthOfLIS(new int[]{3, 4, 5, 1, 2});
    }
}
