package arrays.queues;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class TrappingRainWater {

    public int trap(int[] height) {

        int left = 0, right = height.length - 1;
        int ans = 0, left_max = 0, right_max = 0;

        while(left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) left_max = height[left];
                else ans += left_max - height[left];
                left++;
            } else {
                if (height[right] >= right_max) right_max = height[right];
                else ans += right_max - height[right];
                right--;
            }
        }

        return ans;
    }
}
