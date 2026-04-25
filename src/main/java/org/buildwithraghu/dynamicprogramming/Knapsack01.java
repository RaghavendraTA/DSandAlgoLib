package org.buildwithraghu.dynamicprogramming;

public class Knapsack01 {

    public int knapsack(int cap, int[] values, int[] weights) {
        int n = values.length;
        int[][] dp = new int[cap+1][n];

        for(int i = n-1; i >= 0; i--) {
            for(int c = 1; c <= cap; c++) {
                if (weights[i] <= c)
                    dp[i][c] = Math.max(values[i] + dp[i+1][c-weights[i]], dp[i+1][c]);
                else
                    dp[i][c] = dp[i+1][c];
            }
        }
        return dp[0][cap];
    }
}
