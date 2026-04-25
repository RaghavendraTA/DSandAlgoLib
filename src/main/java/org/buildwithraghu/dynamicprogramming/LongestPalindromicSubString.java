package org.buildwithraghu.dynamicprogramming;

import java.util.Arrays;

public class LongestPalindromicSubString {
    // https://leetcode.com/problems/longest-palindromic-substring/
    public String longestPalindrome_dp(String s) {
        int n = s.length();
        if (n < 2) return s;

        int start = 0, maxLen = 1;
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], false);
            dp[i][i] = true;
        }

        for(int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1))
                dp[i][i+1] = true;
            if (dp[i][i+1]) {
                start = i;
                maxLen = 2;
            }
        }

        for(int l = 3; l <= n; l++) {
            for(int i = 0; i < n-l+1; i++) {
                int j = i+l-1;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLen = l;
                }
            }
        }
        return s.substring(start, start+maxLen);
    }

    // searching around from center
    // https://leetcode.com/problems/longest-palindromic-substring
    public String longestPalindrome(String s) {
        int[] ans = new int[]{0, 0};
        for(int i = 0; i < s.length(); i++) {
            int[] l = expandFromCenter(s, i, i);
            int[] r = expandFromCenter(s, i, i+1);
            if (l[1]-l[0] > ans[1]-ans[0]) {
                ans = l;
            }
            if (r[1]-r[0] > ans[1]-ans[0]) {
                ans = r;
            }
        }
        return s.substring(ans[0], ans[1]);
    }

    int[] expandFromCenter(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return new int[]{start+1, end};
    }

    // https://leetcode.com/problems/palindromic-substrings
    public int countSubstrings(String s) {
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            ans += expandFromCenterSubStrings(s, i, i);
            ans += expandFromCenterSubStrings(s, i, i+1);
        }
        return ans;
    }

    int expandFromCenterSubStrings(String s, int i, int j) {
        int c = 0;
        while(i >= 0 && j < s.length()) {
            if (s.charAt(i) != s.charAt(j))
                break;
            else
                c++;
            i--;
            j++;
        }
        return c;
    }
}
