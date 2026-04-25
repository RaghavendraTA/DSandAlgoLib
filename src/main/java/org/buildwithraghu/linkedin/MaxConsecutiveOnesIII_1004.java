package org.buildwithraghu.linkedin;

public class MaxConsecutiveOnesIII_1004 {

    public int longestOnes(int[] nums, int k) {
        int maxLen = 0, i = 0, zero = 0;
        for(int j = 0; j < nums.length; j++) {
            if (nums[j] == 0)
                zero++;
            while(zero > k) {
                if (nums[i] == 0)
                    zero--;
                i++;
            }
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }
}
