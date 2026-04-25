package org.buildwithraghu.slidingWindow;

public class MaxAvgSubArray {
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for(int i = 0; i < k; i++)
            sum += nums[i];
        double ans = (double) sum / k;
        for(int i = k; i < nums.length; i++) {
            sum -= nums[i-k];
            sum += nums[i];
            ans = Math.max(ans, (double) sum / k);
        }
        return ans;
    }
}
