package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

import java.util.Arrays;
import java.util.List;

public class MinDifficultyJobScheduling {

    public static int SomeLargeNumber = Integer.MAX_VALUE/2;

    public int minDifficulty(int[] jobDifficulty, int d) {

        int n = jobDifficulty.length;
        if(n < d) return -1;

        int[][] dp = new int[n+1][d+1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], SomeLargeNumber);
        }

        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int k = 1; k <= d; k++){
                int max = 0;
                for(int j = i; j >= k; j--){
                    max = Math.max(max,jobDifficulty[j-1]);
                    dp[i][k] =  Math.min(dp[i][k],dp[j-1][k-1] + max);
                }
            }
        }

        return dp[n][d];
    }
}
