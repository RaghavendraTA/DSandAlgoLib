package org.buildwithraghu.Combinatorics;

import java.util.*;

public class Permutations {

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void reverse(int[] arr, int i) {
        int p = i, q = arr.length - 1;
        while(p < q) {
            swap(arr, p, q);
            p++;
            q--;
        }
    }

    public static boolean nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // From the right find an element which is greater or equal to next number
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // if 'i' < 0 or no element found then reverse the complete arr and return
        if (i >= 0) {
            int j = nums.length - 1;
            // From the right find j smaller or equal to i then swap
            while (nums[j] <= nums[i])
                j--;
            swap(nums, i, j);
        } else {
            return false;
        }
        // reverse everything after i
        reverse(nums, i+1);
        return true;
    }

    public static void permutation(int[] arr) {
        do {
            System.out.println(Arrays.toString(arr));
        } while(nextPermutation(arr));
    }

    public static void permutationsUsingSwaps(int[] nums, int i) {
        if (i == nums.length) {
            System.out.println(Arrays.toString(nums));
            return;
        }
        for(int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            permutationsUsingSwaps(nums, i+1);
            swap(nums, i, j);
        }
    }

    public static void main(String[] args) {
        permutationsUsingSwaps(new int[]{1, 2, 3}, 0);
        System.out.println("-----------");
        permutation(new int[]{1, 2, 3});
    }
}
