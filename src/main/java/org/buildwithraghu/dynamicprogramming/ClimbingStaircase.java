package org.buildwithraghu.dynamicprogramming;

public class ClimbingStaircase {

    // https://leetcode.com/problems/climbing-stairs
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++)
            dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }

    // Rec-relation
    public int climbStairs_rec(int n) {
        if (n == 0) return 1;   // one valid way (no steps left)
        if (n < 0) return 0;    // no valid way
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
