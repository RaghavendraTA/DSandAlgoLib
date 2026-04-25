package org.buildwithraghu.pointers;

import java.util.Arrays;

public class ProductOfArrayWithoutItself {

    // Easy to understand code
    public int[] productExceptSelf_easyunderstand(int[] nums) {
        int[] suffix = new int[nums.length];
        int[] postfix = new int[nums.length];

        suffix[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            suffix[i] = suffix[i-1] * nums[i];
        }

        postfix[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length - 2; i >= 0; i--) {
            postfix[i] = postfix[i+1] * nums[i];
        }

        int[] output = new int[nums.length];
        output[0] = postfix[1];
        output[nums.length-1] = suffix[nums.length-2];
        for(int i = 1; i < nums.length-1; i++) {
            output[i] = suffix[i-1] * postfix[i+1];
        }
        // From out[i] = left_product * right_product
        return output;
    }

    // memory optimization
    public int[] productExceptSelf_memopt(int[] nums) {
        int[] suffix = new int[nums.length];
        int[] postfix = new int[nums.length];

        suffix[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            suffix[i] = suffix[i-1] * nums[i];
        }

        postfix[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length - 2; i >= 0; i--) {
            postfix[i] = postfix[i+1] * nums[i];
        }

        int temp = suffix[suffix.length-2];
        for(int i = nums.length-2; i > 0; i--) {
            suffix[i] = suffix[i-1] * postfix[i+1];
        }

        suffix[0] = postfix[1];
        suffix[suffix.length-1] = temp;
        return suffix;
    }

    // Completely memory optimized
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

    public static void main(String[] args) {
        ProductOfArrayWithoutItself pp = new ProductOfArrayWithoutItself();
        System.out.println(Arrays.toString(pp.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(pp.productExceptSelf(new int[]{5, 2, 3, 1, 4})));
    }
}
