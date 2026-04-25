import java.util.*;

class Solution {
	
	public int trap(int[] height) {
        int n = height.length, ans = 0;
		int l = 0, r = n-1;
		int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
		while(l < r) {
			if (height[l] < height[r]) {
				if (height[l] > leftMax)
					leftMax = height[l];
				else
					ans += leftMax - height[l];
				l++;
			} else {
				if (height[r] > rightMax)
					rightMax = height[r];
				else
					ans += rightMax - height[r];
				r--;
			}
		}
		return ans;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}