package org.buildwithraghu.pointers;

public class TrappingRainWater {

    // https://leetcode.com/problems/trapping-rain-water
    public int trap(int[] height) {
        int ans = 0, leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
        int l = 0, r = height.length-1;
        while(l < r) {
            if (height[l] < height[r]) {
                if (height[l] > leftMax)
                    leftMax = height[l];
                else
                    ans += leftMax-height[l];
                l++;
            } else {
                if (height[r] > rightMax)
                    rightMax = height[r];
                else
                    ans += rightMax-height[r];
                r--;
            }
        }
        return ans;
    }
}
