package org.buildwithraghu.pointers;

import java.util.Arrays;

public class PalindromicSubstrings {

    // https://leetcode.com/problems/palindromic-substrings
    public int countSubstrings_slow(String s) {
        int count = s.length(), n = s.length();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                String p = getPalindrome(s, i, j);
                if (p != null) {
                    System.out.println(p);
                    count++;
                }
            }
        }
        return count;
    }

    private String getPalindrome(String s, int p, int q) {
        int l = p, r = q;
        while(l < r) {
            if (s.charAt(l) != s.charAt(r))
                return null;
            l++;
            r--;
        }
        return s.substring(p, q+1);
    }

    // dp solution
    public int countSubstrings_dp(String s) {
        int n = s.length(), c = 1;
        boolean[][] dp = new boolean[n+1][n+1];
        Arrays.fill(dp[0], false);
        dp[0][0] = true;

        // length of 1
        for(int i = 1; i < n; i++) {
            Arrays.fill(dp[i], false);
            dp[i][i] = true;
            c++;
        }

        // length of 2
        for(int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i + 1] = true;
                c++;
            }
        }

        // length above 3
        for(int l = 3; l <= n; l++) {
            for(int i = 0; i <= n-l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    c++;
                }
            }
        }
        return c;
    }

    // expandAround logic - this is much faster for some reason
    public int countSubstrings(String s) {
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            ans += expandFromCenter(s, i, i);
            ans += expandFromCenter(s, i, i+1);
        }
        return ans;
    }

    public int expandFromCenter(String s, int i, int j) {
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


    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        System.out.println(ps.countSubstrings("ab"));
    }
}
