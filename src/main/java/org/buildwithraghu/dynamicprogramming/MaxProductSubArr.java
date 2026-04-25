package org.buildwithraghu.dynamicprogramming;

public class MaxProductSubArr {
    public int maxProduct(int[] nums) {
        if (nums.length == 0)
            return 0;
        int maxSoFar = nums[0], minSoFar = nums[0];
        int ans = maxSoFar, temp_max = 0;

        for(int i = 1; i < nums.length; i++) {
            temp_max = Math.max(nums[i], Math.max(maxSoFar * nums[i], minSoFar * nums[i]));
            minSoFar = Math.min(nums[i], Math.min(maxSoFar * nums[i], minSoFar * nums[i]));
            maxSoFar = temp_max;
            ans = Math.max(ans, maxSoFar);
        }

        return ans;
    }
}
