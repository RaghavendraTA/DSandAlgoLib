package dynamicprogramming.maxsubarray;

/*
 * created by raghavendra.ta on 14-Jul-2021
 */


public class MaxSumSubArrayWithNoKContiguousNumbers {

    /**
     *           |   max{A[i] + dp[i-2], dp(i-1)}   if i > 2
     * dp(i) =   {   A[1]                           if i == 1
     *           |   max{A[1], A[2]}                if i == 2
     */
    static int maxSubArrayWithNoTwoContiguous(int[] A) {

        int n = A.length;
        int[] dp = new int[n + 1];
        dp[0] = A[0];
        dp[1] = Math.max(A[0], A[1]);

        for(int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i]);
        }

        return dp[n-1];
    }

    /**
     *              |   A[i] + A[i-1] + dp(i-3)
     * dp(i) = max  {   A[i] + dp(i-2)
     *              |   M(i-1)
     */
    static int maxSubArrayWithNoThreeContiguous(int[] A, int n) {
        return -1;
    }
}
