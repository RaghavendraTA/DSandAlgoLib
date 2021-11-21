package dynamicprogramming.subsetsum;

/*
 * created by raghavendra.ta on 26-Jun-2021
 */

import java.util.Arrays;

public class CoinChange {

    public static long mod = 1000000007;

    static int countWays(int n, int k) {

        int[] count = new int[n + 1];
        count[0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= i)
                    count[j] += (count[j - i] % mod);
            }
        }
        return count[n];
    }

    public static int counter = 0;

    static void countWaysUsingRecursion(int idx, int n, int k) {
        if (n == 0) {
            counter++;
            return;
        }
        for(int i = idx; i <= k; i++) {
            if (n - i >= 0)
                countWaysUsingRecursion(i, n - i, k);
        }
    }


    /**
     *
     * 1. Exclude coin
     * 2. Include the coin
     * 3. Add 1 and 2 from above
     *
     */
    public static void waysToCoinChange(int[] denom, int k) {
        int n = denom.length;
        int[][] dp = new int[n][k+1];

        // When k = 0 then there is 1 possibility of picking denomination as we don't to use any coins
        // This dp[i][0] = 1;
        //
        for(int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        // now for the first row can number denom[0] coins make sum j (where j iterating from 1 to k)
        //              | then there is 1 possibility
        //  dp[0][j] =  | if not then assign 0
        //
        for(int j = 1; j <= k; j++) {
            if (j >= denom[0] && j % denom[0] == 0)
                dp[0][j] = 1;
            else
                dp[0][j] = 0;
        }

        // if denom[i] > j then
        //      copy above cell dp[i][j] = dp[i-1][j]
        // else
        //      (exclude denom[i] => dp[i-1][j]) + (include denom[i] => dp[i][j-denom[i]])
        //      dp[i][j] = dp[i-1][j] + dp[i][j-denom[i]]
        //
        for(int i = 1; i < n; i++) {
            for(int  j = 0; j <= k; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= denom[i])
                    dp[i][j] += dp[i][j-denom[i]];
            }
        }

        System.out.println(dp[n-1][k]);
    }

    /**
     *
     * 1. Exclude coin
     * 2. Include the coin
     * 3. Add 1 and 2 from above
     *
     */
    public static int coinChange(int[] coins, int amount) {

        if (amount == 0)
            return 0;

        else if (coins.length == 1) {
            if (amount >= coins[0] && amount % coins[0] == 0)
                return amount / coins[0];
            else
                return -1;
        }

        Arrays.sort(coins);

        int n = coins.length;
        int[][] dp = new int[n][amount+1];

        // When k = 0 then min number coins required is also 0, do dp[i][0] = 0
        //
        // when i == 0 then
        // loop j from 0...k:
        //      if (j % coins[0] == 0) that means this single coin itself can sum upto j
        //          dp[0][j] = j / coins[0]
        //      else
        //          dp[0][j] = 0;
        //
        for(int j = 1; j <= amount; j++) {
            if (j % coins[0] == 0)
                dp[0][j] = j / coins[0];
            else
                dp[0][j] = 0;
        }

        // for remaining coins
        //
        // if (j < coins[i])
        //      dp[i][j] = dp[i-1][j]
        // else
        //      dp[i][j] = Math.min(dp[i-1][j], 1 + dp[i][j-coins[i]]
        //
        for(int i = 1; i < n; i++) {
            for(int  j = 0; j <= amount; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= coins[i])
                    dp[i][j] = Math.min(dp[i-1][j], 1 + dp[i][j-coins[i]]);
            }
        }

        for(int[] arr: dp)
            System.out.println(Arrays.toString(arr));

        return dp[n-1][amount];
    }

    public static void main(String[] args) {
        //System.out.println(countWays(7, 3));
        //countWaysUsingRecursion( 1, 7, 3);
        //System.out.println(counter);
        //System.out.println(coinChange(new int[]{186,419,83,408}, 6249));
    }
}
