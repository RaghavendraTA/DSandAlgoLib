package org.buildwithraghu.linkedin;

public class MaximumProductSubArray {

    // mx = max(n, mx * n, mn * n)
    // mn = min(n, mx * n, mn * n)
    // ans = max(ans, mx)
    public int maxProduct(int[] nums) {
        int minSoFar = nums[0], maxSoFar = nums[0], ans = nums[0], temp;
        for(int i = 1; i < nums.length; i++) {
            temp = Math.max(nums[i], Math.max(maxSoFar * nums[i], minSoFar * nums[i]));
            minSoFar = Math.min(nums[i], Math.min(maxSoFar * nums[i], minSoFar * nums[i]));
            maxSoFar = temp;
            ans = Math.max(ans, maxSoFar);
        }
        return ans;
    }
}
