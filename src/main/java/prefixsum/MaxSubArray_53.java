package prefixsum;

public class MaxSubArray_53 {

    public int maxSubArray(int[] nums) {
        int maxSub = nums[0], n = nums.length;
        int sum = nums[0];
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
