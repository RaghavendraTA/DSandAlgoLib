package org.buildwithraghu.greedy;

public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int sum = nums[0], ans = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (sum + nums[i] > nums[i])
                sum += nums[i];
            else
                sum = nums[i];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
