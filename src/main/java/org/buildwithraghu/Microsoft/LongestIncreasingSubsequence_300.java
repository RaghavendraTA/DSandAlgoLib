import java.util.*;

class Solution {
	
	public int lengthOfLIS(int[] nums) {
        int n = nums.length, s;
		List<Integer> data = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			s = Collections.binarySearch(data, nums[i]);
			if (s < 0)
				s = -(s + 1);
			if (s == data.size())
				data.add(nums[i]);
			else
				data.set(s, nums[i]);
		}
		return data.size();
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}