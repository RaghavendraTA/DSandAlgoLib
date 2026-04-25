package org.buildwithraghu.search;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class FindMinInRotatedSorted {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l < r) {
            int mid = (l+r) / 2;
            if (nums[mid] > nums[r])
                l = mid + 1;
            else
                r = mid;
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,5,1,2};
        FindMinInRotatedSorted f = new FindMinInRotatedSorted();
        System.out.println(f.findMin(arr));
    }
}
