package org.buildwithraghu.Microsoft.dp;

import java.util.*;

public class LongestCommonSubsequence {

    public int lcsRec(String a, String b) {
        return lcsRec(a, b, 0, 0);
    }

    private int lcsRec(String a, String b, int i, int j) {
        if (i >= a.length() || j >= b.length())
            return 0;
        if (a.charAt(i) == b.charAt(j)) {
            return 1 + lcsRec(a, b, i + 1, j + 1);
        }
        return Math.max(lcsRec(a, b, i + 1, j), lcsRec(a, b, i, j + 1));
    }

    // lcs memo
    public int lcsMemo(String a, String b) {
        int[][] memo = new int[a.length() + 1][b.length() + 1];
        return lcsMemo(a, b, memo, 0, 0);
    }

    private int lcsMemo(String a, String b, int[][] memo, int i, int j) {
        if (i >= a.length() || j >= b.length())
            return 0;
        if (memo[i][j] != 0)
            return memo[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            memo[i][j] = 1 + lcsMemo(a, b, memo, i + 1, j + 1);
            return memo[i][j];
        }
        memo[i][j] = Math.max(
            lcsMemo(a, b, memo, i + 1, j), 
            lcsMemo(a, b, memo, i, j + 1)
        );
        return memo[i][j];
    }

    // lcs dp
    public int lcsDP(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];

        // dp is already initialized to 0, so base cases are handled
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "ABCBDAB";
        String text2 = "BDCABA";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.print(lcs.lcsDP(text1, text2));
    }
}