package org.buildwithraghu.dynamicprogramming;

import java.util.Stack;

public class NextLargestNoToTheRight {

    public int[] nextLargestNoToTheRight(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Stack<Integer> stk = new Stack<>();

        for(int i = n-1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peek() <= nums[i])
                stk.pop();
            ans[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(nums[i]);
        }

        return ans;
    }
}
