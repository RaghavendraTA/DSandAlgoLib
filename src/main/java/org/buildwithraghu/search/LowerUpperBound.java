package org.buildwithraghu.search;

public class LowerUpperBound {

    // lower_bound O(log(n))
    public int lowerBound(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > k)
                right = mid - 1;
            else
                left = mid;
        }
        return left;
    }

    // upper_bound O(log(n))
    public int upperBound(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = ((left + right) / 2) + 1;
            if (nums[mid] > k)
                right = mid - 1;
            else
                left = mid;
        }
        return nums[right] == k ? right : -1;
    }
}
