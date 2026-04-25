package org.buildwithraghu.dynamicprogramming;

import java.util.Arrays;

public class PalindromicSubstring {

    // https://leetcode.com/problems/palindromic-substrings/description/
    public int countSubstrings(String s) {
        int n = s.length(), count = 1;

        boolean[][] dp = new boolean[n][n];
        Arrays.fill(dp[0], false);
        dp[0][0] = true;

        for(int i = 1; i < n; i++) {
            Arrays.fill(dp[i], false);
            dp[i][i] = true;
            count++;
        }

        for(int i = 0; i < n-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                count++;
            }
        }

        for(int l = 3; l <= n; l++) {
            for(int i = 0; i < n-l+1; i++) {
                int j = i+l-1;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
