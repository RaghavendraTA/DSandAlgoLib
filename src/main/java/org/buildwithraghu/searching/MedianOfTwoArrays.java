package org.buildwithraghu.searching;

import java.util.*;

public class MedianOfTwoArrays {
    public double findMedianSortedArrays_ON(int[] nums1, int[] nums2) {
        // Using O(m+n)
        int m = nums1.length, n = nums2.length;
        int medianIndex2 = (m + n) / 2;

        int i = 0, j = 0, count = 0;
        int left = 0, right = 0;

        // Merge until we reach the right median index
        while (count <= medianIndex2) {
            left = right;
            if (i < m && (j >= n || nums1[i] <= nums2[j])) {
                right = nums1[i++];
            } else {
                right = nums2[j++];
            }
            count++;
        }

        // If odd length → right is the median
        if ((m + n) % 2 == 1) {
            return right;
        }
        // If even length → average of left and right
        return (left + right) / 2.0;
    }

    public double findMedianSortedArrays_OLOGN(int[] nums1, int[] nums2) {
        // Using binary search O(log(n))
        if (nums1.length == 0 && nums2.length == 0)
            return -1;
        if (nums2.length < nums1.length) {
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }

        int m = nums1.length, n = nums2.length;
        int l = 0, r = m;

        return 1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        MedianOfTwoArrays md = new MedianOfTwoArrays();
        System.out.println(md.findMedianSortedArrays_ON(nums1, nums2));
    }
}

/*
Doesn't work
[1 3 5] [6 8 10 12]
mid1 = 3
mid2 = (8+10)/2 = 9
fmid = (3+9)/2 = 12/2 = 6

[1, 3, 5] [2, 4, 6]
mid1 = 3
mid2 = 4;
fmid = (3+4)/2 = 3.5

[1 3 5 6] [10 11 12]
mid1 = 3+5 / 2 = 4
mid2 = 11
fmid = (4 + 11) / 2 = 7.5
*/