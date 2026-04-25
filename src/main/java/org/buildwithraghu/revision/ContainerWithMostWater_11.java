package org.buildwithraghu.revision;

public class ContainerWithMostWater_11 {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while(l < r) {
            int temp = (r - l) * Math.min(height[l], height[r]);
            ans = Math.max(ans, temp);
            if (height[l] < height[r]) l++;
            else r--;
        }
        return ans;
    }
}
