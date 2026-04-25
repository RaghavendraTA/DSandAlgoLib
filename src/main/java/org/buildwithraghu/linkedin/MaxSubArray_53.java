package org.buildwithraghu.linkedin;

public class MaxSubArray_53 {

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], sum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (sum + nums[i] > nums[i]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}
