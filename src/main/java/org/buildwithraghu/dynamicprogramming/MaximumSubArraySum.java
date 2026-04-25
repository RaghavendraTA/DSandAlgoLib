package org.buildwithraghu.dynamicprogramming;

public class MaximumSubArraySum {

    // https://leetcode.com/problems/maximum-subarray/
    public int maxSubArray(int[] nums) {
        int sum = nums[0], n = nums.length;
        int maxSub = sum;
        for(int i = 1; i < n; i++) {
            if (sum + nums[i] > nums[i])
                sum += nums[i];
            else
                sum = nums[i];
            maxSub = Math.max(maxSub, sum);
        }
        return maxSub;
    }
}
