package org.buildwithraghu.revision;

public class FirstAndLastInSortedArray_34 {

    class Solution {

        public int[] searchRange(int[] nums, int target) {
            int[] invalid = new int[]{-1, -1};
            if (nums.length == 0)
                return invalid;

            int lowerBound = lowerBoundSearch(nums, target);
            if (lowerBound == -1)
                return invalid;

            int upperBound = upperBoundSearch(nums, target);
            return new int[]{lowerBound, upperBound};
        }

        private int lowerBoundSearch(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] < target)
                    l = mid + 1;
                else
                    r = mid;
            }
            return nums[l] == target ? l : -1;
        }

        private int upperBoundSearch(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = (l + r) / 2 + 1;
                if (nums[mid] > target)
                    r = mid - 1;
                else
                    l = mid;
            }
            return nums[l] == target ? l : -1;
        }
    }
}
