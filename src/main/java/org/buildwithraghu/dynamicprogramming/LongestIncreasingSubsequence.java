package org.buildwithraghu.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n== 0)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    // Using lower_bound solution
    public int lengthOfLIS_lb(int[] nums) {
        List<Integer> sub = new ArrayList<>();

        for(int num: nums) {
            int idx = Collections.binarySearch(sub, num);
            if (idx < 0)
                idx = -(idx + 1);
            if (idx == sub.size())
                sub.add(num);
            else
                sub.set(idx, num);
        }

        return sub.size();
    }
}
