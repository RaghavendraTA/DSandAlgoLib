class Solution {
	
	private int getPivot(int[] nums) {
		int l = 0, r = nums.length - 1, n = nums.length;
		while(l < r) {
			int mid = (l + r) / 2;
			if (mid > 0 && nums[mid-1] > nums[mid])
				return mid - 1;
			if (mid < n-1 && nums[mid] > nums[mid+1])
				return mid;
			if (nums[mid] < nums[r])
				r = mid;
			else
				l = mid + 1;
		}
		return l;
	}
	
	private int binarySearch(int[] nums, int l, int r, int target) {
		if (l > r)
			return -1;
		int mid = (l + r) / 2;
		if (nums[mid] == target)
			return mid;
		if (nums[mid] < target)
			return binarySearch(nums, mid + 1, r, target);
		return binarySearch(nums, l, mid - 1, target);
	}
	
	public int search(int[] nums, int target) {
		// p is inclusive
		int p = getPivot(nums);
		int l = binarySearch(nums, 0, p, target);
		int r = binarySearch(nums, p + 1, nums.length - 1, target);
        return l == -1 ? r : l;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.search(new int[]{1, 2, 3, 4}, 0));
	}
}