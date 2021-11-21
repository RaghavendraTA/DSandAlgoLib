package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 29-Jun-2021
 */

import java.util.Arrays;


/**
 *
 */
public class Knapsack {

    public static int maxValue = 0;

    public static void usingRecursion(int value, int c, int[] s, int[] v) {

        if (c == 0) {
            maxValue = Math.max(maxValue, value);
        }
        else if (c < 0) {
            return;
        }
        for (int i = 0; i < s.length; i++)
            usingRecursion(v[i] + value, c - s[i], s, v);
    }

    public static void usingDP(int c, int[] w, int[] v) {

        int n = w.length;
        int[][] dp = new int[n][c + 1];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                if (w[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }
        for (int[] arr : dp)
            System.out.println(Arrays.toString(arr));
        System.out.println(dp[n - 1][c]);
    }

    public static void main(String[] args) {
        usingRecursion(0, 5, new int[]{1, 4, 2, 7, 5}, new int[]{1, 1, 5, 2, 7});
        System.out.println(maxValue);
        //usingDP(5, new int[]{1, 4, 2, 7, 5}, new int[]{1, 1, 5, 2, 7});
        //usingDP(11, new int[]{1, 2, 5, 6, 7}, new int[]{1, 6, 18, 22, 28});
    }
}
