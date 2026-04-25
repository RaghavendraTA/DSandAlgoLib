import java.util.*;

class Solution {
	
	public int maxSubArray(int[] nums) {
        int csum = 0, ans = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			if (nums[i] > csum + nums[i])
				csum = nums[i];
			else
				csum += nums[i];
			ans = Math.max(ans, csum);
		}
		return ans;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}