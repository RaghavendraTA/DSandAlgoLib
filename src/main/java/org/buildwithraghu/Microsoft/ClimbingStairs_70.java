class Solution {
	
	public int climbStairs(int n) {
        if (n < 3) 
            return n;
        int p = 1;
        int q = 2;
        int ans = 0;
        for(int i = 3; i <= n; i++) {
            ans = p + q;
            p = q;
            q = ans;
        }
        return q;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}