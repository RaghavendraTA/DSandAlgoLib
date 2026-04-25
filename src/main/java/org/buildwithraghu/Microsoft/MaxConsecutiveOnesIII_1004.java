class Solution {
	
	public int longestOnes(int[] nums, int k) {
        int l = 0, z = 0, ans = 0;
        for(int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) {
                z++;
            }
            while(z > k && l < r) {
                if (nums[l] == 0)
                    z--;
                l++;
            }
            if (z <= k) {
                ans = Math.max(ans, r - l + 1);
            }
        }
        return ans;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}