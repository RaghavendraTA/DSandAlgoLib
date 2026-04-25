class Solution {
	
	public boolean isPalindrome(int x) {
		int y = 0, t = x;
        while(t > 0) {
			y = (y * 10) + (t % 10);
			t /= 10;
		}
		return x == y;
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.isPalindrome(101));
		System.out.println(s.isPalindrome(102));
	}
}