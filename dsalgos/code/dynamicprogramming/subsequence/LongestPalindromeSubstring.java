package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

/**
 *
 * L(i, j) = 1 if A[i]...A[j] is palindrome else 0
 *
 * L(i, i) = 1
 * L(i, j) = L[i, i+1], if A[i] == A[i+1], for 1 <= i <= j <= n-1
 * L(i, j) = L[i+1, j-1] if A[i] == A[j]
 */
public class LongestPalindromeSubstring {

    public static int lps(String str) {
        int max = 1;
        int n = str.length();
        int[][] dp = new int[n][n];

        for(int i = 1; i < n; i++) {
            dp[i][i] = 1;
            if (str.charAt(i) == str.charAt(i+1)) {
                dp[i][i+1] = 1;
                max = 2;
            }
            else {
                dp[i][i+1] = 0;
            }
        }

        for(int k = 3; k <= n; k++) {
            for(int i = 1; i <= n-k+1; i++) {
                int j = i + k - 1;
                if (str.charAt(i) == str.charAt(j) && dp[i+1][j-1] == 1) {
                    dp[i][j] = 1;
                    max = k;
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }

        return max;
    }
}
