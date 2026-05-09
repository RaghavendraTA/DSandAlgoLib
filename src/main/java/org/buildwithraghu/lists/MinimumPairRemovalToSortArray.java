package org.buildwithraghu.lists;

public class MinimumPairRemovalToSortArray {

    public int minimumPairRemoval(int[] nums) {
        int minV = nums[0];
        int i = 0, ans = 0;
        while(i < nums.length && minV <= nums[i])
            minV = nums[i++];
        int j = i;
        while(j < nums.length && i < nums.length) {
            if (minV > nums[j]) {
                nums[j] += nums[i++];
                ans++;
            } else {
                minV = nums[j];
                j++;
            }
        }
        return (j < nums.length && minV > nums[j]) ? 0 : ans;
    }
}
