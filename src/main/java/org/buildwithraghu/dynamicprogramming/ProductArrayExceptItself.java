package org.buildwithraghu.dynamicprogramming;

public class ProductArrayExceptItself {

    // https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums) {
        int[] suffix = new int[nums.length];

        suffix[0] = 1;
        for(int i = 1; i < nums.length; i++) {
            suffix[i] = suffix[i-1] * nums[i-1];
        }

        // P will hold product of all elements to the right
        int P = 1;
        for(int i = nums.length-1; i >= 0; i--) {
            suffix[i] *= P;
            P *= nums[i];
        }
        return suffix;
    }
}
