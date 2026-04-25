package org.buildwithraghu.search;

import java.util.Arrays;

public class SearchInSortedRotatedArray {

    // https://leetcode.com/problems/search-in-rotated-sorted-array
    public int pivot(int[] nums, int l, int r) {
        if (l > r) return -1;
        int mid = l + (r-l) / 2;
        if (mid + 1 < nums.length && nums[mid] > nums[mid+1]) {
            return mid;
        }
        if (mid > 0 && nums[mid-1] > nums[mid]) {
            return mid-1;
        }
        if (nums[mid] < nums[r]) {
            return pivot(nums, l, mid-1);
        }
        return pivot(nums, mid + 1, r);
    }

    public int search(int[] nums, int target) {
        int p = pivot(nums, 0, nums.length-1);

        if (p < 0)
            return Math.max(-1, Arrays.binarySearch(nums, target));

        if (nums[p] == target)
            return p;

        return Math.max(
            Math.max(-1, Arrays.binarySearch(nums, 0, p, target)),
            Math.max(-1, Arrays.binarySearch(nums, p+1, nums.length, target))
        );
    }

    public static void main(String[] args) {
        SearchInSortedRotatedArray sr = new SearchInSortedRotatedArray();
        System.out.println(sr.search(new int[]{6,7,8,1,2,3,4,5}, 6));
    }
}
