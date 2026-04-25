package dynamicprogramming.subsetsum;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Arrays;

/**
 * subsetSum(A, n, sum/2) = isSubsetSum(A, n-1, sum/2) || subsetSum(A, n-1, sum/2 - A[n-1])
 */
public class SubsetPartition {

    // Recurrence solution
    boolean subsetSum(int[] A, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0)
            return false;
        if (A[n-1] > sum)
            return subsetSum(A, n-1, sum);
        return subsetSum(A, n-1, sum) || subsetSum(A, n-1, sum-A[n-1]);
    }

    boolean findPartition(int[] A, int n) {
        int sum = 0;
        for(int i = 0; i < n; i++)
            sum += A[i];
        if (sum % 2 != 0)
            return false;
        return subsetSum(A, n, sum/2);
    }

    // DP solution works if the input size is smaller

    boolean findPartitionUsingDP(int[] A, int n) {
        int sum = Arrays.stream(A).sum();

        if (sum % 2 != 0)
            return false;

        boolean[][] dp = new boolean[(sum/2)+1][n+1];

        for(int i = 0; i <= n; i++)
            dp[0][i] = true;

        for(int i = 1; i <= sum / 2; i++)
            dp[i][0] = false;

        for(int i = 0; i <= sum / 2; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j-1];
                if (i >= A[j-1])
                    dp[i][j] = dp[i][j] || dp[i-A[j-1]][j-1];
            }
        }
        
        return dp[sum/2][n];
    }
}
