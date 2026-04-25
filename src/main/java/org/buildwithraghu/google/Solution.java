
import java.util.*;

class Solution {

	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		return ksum(nums, target, 0, 4);
	}

	private List<List<Integer>> ksum(int[] nums, int target, int start, int k) {
		return null;
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("End");
	}
}
