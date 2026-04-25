import java.util.*;

/*
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.
*/

class Solution {
	
	public int[] expandFromCenter(String s, int i, int j) {
		while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		return new int[]{j - i, i+1, j};
	}
	
	public String longestPalindrome(String s) {
		int[] ans = new int[]{0, 0};
        for(int i = 0; i < s.length(); i++) {
			int[] l = expandFromCenter(s, i, i);
			int[] r = expandFromCenter(s, i, i + 1);
			if (ans[0] < l[0])
				ans = l;
			if (ans[0] < r[0])
				ans = r;
		}
		return s.substring(ans[1], ans[2]);
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}