import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i-1])
                continue;
			int target = -nums[i];
			int p = i+1, q = n-1;
			while(p < q) {
				if (nums[p] + nums[q] == target) {
					ans.add(Arrays.asList(nums[i], nums[p], nums[q]));
					p++;
					q--;
					while (p < q && nums[p-1] == nums[p]) p++;
					while (p < q && nums[q] == nums[q+1]) q--;
				}
				else if (target < nums[p] + nums[q]) {
					q--;
				}
				else {
					p++;
				}
			}
		}
		return ans;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.threeSum(new int[]{-1,0,1,2,-1,-4}));
	}
}