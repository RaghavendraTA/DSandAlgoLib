package org.buildwithraghu.dynamicprogramming;

public class HouseRobber {

    // https://leetcode.com/problems/house-robber/
    public int rob_1(int[] nums) {
        if (nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n + 1];

        dp[n] = 0;
        dp[n-1] = nums[n-1];

        for(int i = n-2; i >= 0; i--) {
            dp[i] = Math.max(dp[i+1], dp[i+2] + nums[i]);
        }
        return dp[0];
    }

    // https://leetcode.com/problems/house-robber-ii/
    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(helper(nums, 1, nums.length),
                helper(nums, 0, nums.length-1));
    }

    private int helper(int[] nums, int start, int end) {
        int rob1 = 0, rob2 = 0;
        for (int i = start; i < end; i++) {
            int curr = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = curr;
        }
        return rob2;
    }
}
