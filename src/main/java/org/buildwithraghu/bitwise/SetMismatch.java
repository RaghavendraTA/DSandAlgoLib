package org.buildwithraghu.bitwise;

import java.util.Arrays;

public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        boolean[] f = new boolean[nums.length+1];
        Arrays.fill(f, false);
        int r = 0, dup = 0;
        for(int i = 1; i <= nums.length; i++) {
            if (f[nums[i-1]])
                dup = nums[i-1];
            else
                f[nums[i-1]] = true;
            r ^= i ^ nums[i-1];
        }
        return new int[]{dup, r^dup};
    }
}
