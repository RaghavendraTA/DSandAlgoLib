package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 14-Jul-2021
 */

public class MaxAppleCanCollect {

    /**
     *              |               | dp(i, j-1)     if j > 0
     * dp[i, j] =   { A[i][j] + max {
     *              |               | dp(i-1, j)     if i > 0
     */
    static int collect(int[][] A) {
        int n = A.length, m = A[0].length;
        int[][] dp = new int[n][m];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                dp[i][j] = A[i-1][j-1];
                dp[i][j] = Math.max(dp[i][j], dp[i][j] + dp[i][j-1]);
            }
        }

        return dp[n][m];
    }

    // If diagonal direction is allowed
    /**
     *              |               | dp(i, j-1)     if j > 0
     * dp[i, j] =   { A[i][j] + max { dp(i-1, j)     if i > 0
     *              |               | dp(i-1, j-1)   if i > 0 && j > 0
     */

}
