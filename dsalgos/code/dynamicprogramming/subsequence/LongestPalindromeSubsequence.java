package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 02-Jul-2021
 */

import java.util.Arrays;

public class LongestPalindromeSubsequence {

    public static void lps(String str) {

        // Let's consider str[i..j] is the longest palindromic subsequence where 0 < i < j < n
        // if str[i] == str[j] : return longestPS(i+1, j-1) + 2
        // if str[i] != str[j] : return max[longestPS(i+1, j), longestPS(i, j-1)];

        int n = str.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        int j;
        for (int k = 2; k <= n; k++) {
            for (int i = 0; i < n - k + 1; i++) {
                j = i + k - 1;
                if (str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        for(int[] arr: dp)
            System.out.println(Arrays.toString(arr));

        System.out.println(dp[0][n - 1]);
    }

    public static void main(String[] args) {

        lps("raghavendrardnr");
    }
}
