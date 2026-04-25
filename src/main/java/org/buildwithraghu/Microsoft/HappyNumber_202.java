class Solution {
	
	public boolean isHappy(int n) {
        int slow = n, fast = n;
        while(true) {
            slow = getHappy(slow);
            fast = getHappy(getHappy(fast));
            if (fast == 1)
                return true;
            else if (fast == slow)
                return false;
        }
    }

    private int getHappy(int n) {
        int ans = 0, rem = 0;
        while(n > 0) {
            rem = (n % 10);
            ans += (rem * rem);
            n /= 10;
        }
        return ans;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}