package org.buildwithraghu.pointers;

public class ContainerWithMostWater {

    // https://leetcode.com/problems/container-with-most-water
    public int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int maxHold = 0;
        while(l < r) {
            int hold = Math.abs(r-l) * Math.min(height[r], height[l]);
            maxHold = Math.max(maxHold, hold);
            if (height[l] < height[r]) l++;
            else r--;
        }
        return maxHold;
    }
}
