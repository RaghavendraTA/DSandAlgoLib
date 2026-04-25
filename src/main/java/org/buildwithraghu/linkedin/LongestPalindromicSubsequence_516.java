package org.buildwithraghu.linkedin;

public class LongestPalindromicSubsequence_516 {

    public int longestPalindromeSubseq_memo(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return lps(s, 0, n-1, memo);
    }

    private int lps(String s, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (s.charAt(i) == s.charAt(j))
            memo[i][j] = lps(s, i+1, j-1, memo) + 2;
        else
            memo[i][j] = Math.max(lps(s, i + 1, j, memo), lps(s, i, j-1, memo));
        return memo[i][j];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = n-1; i >= 0; i--) {
            dp[i][i] = 1;
            for(int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i+1][j-1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n-1];
    }
}
