package org.buildwithraghu.dynamicprogramming;

public class LongestPossiblePalindrome {

    // https://leetcode.com/problems/longest-palindrome/
    public int longestPalindrome(String s) {
        int[] map = new int[128];
        for(char c: s.toCharArray()) {
            map[c]++;
        }
        int ans = 0;
        boolean hasOdd = false;
        for(int c: map) {
            if (c % 2 == 0) {
                ans += c;
            } else {
                int t = c / 2;
                ans += (t * 2);
                hasOdd = true;
            }
        }
        return ans + (hasOdd ? 1 : 0);
    }
}
