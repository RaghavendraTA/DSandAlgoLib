package org.buildwithraghu.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;

// https://leetcode.com/problems/coin-change/
public class CoinChange {

    // Rec-Relation
    public int minCoins = Integer.MAX_VALUE;

    public int coinChange_rec(int[] coins, int amount) {
        minCoins = Integer.MAX_VALUE;
        coinChange(coins, amount, 0);
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    public void coinChange(int[] coins, int amount, int count) {
        if (amount < 0)
            return;
        if (amount == 0) {
            minCoins = Math.min(minCoins, count);
            return;
        }
        for(int i: coins) {
            if (amount >= i) {
                coinChange(coins, amount - i, count + 1);
            }
        }
    }

    // ----------- Memoization -----------------------------------------------------------------
    HashMap<Integer, Integer> memo = new HashMap<>();

    public int coinChange_memo(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        int res = dfs(coins, amount);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int dfs(int[] coins, int amount) {
        if (amount < 0)
            return Integer.MAX_VALUE;
        if (amount == 0)
            return 0;
        if (memo.containsKey(amount))
            return memo.get(amount);

        int min = Integer.MAX_VALUE;
        for (int c : coins) {
            int sub = dfs(coins, amount - c);
            if (sub != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + sub);
            }
        }

        memo.put(amount, min);
        return min;
    }

    // ----------- Dynamic Programming (Sub-Problems) ---------------------------------------------
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int c: coins) {
                if (c <= i)
                    dp[i] = Math.min(dp[i], dp[i-c]+1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    public static void main(String[] args) {
        // Rec-Relation
        CoinChange cc = new CoinChange();
        cc.coinChange(new int[]{1,2,5}, 11, 0);
        System.out.println(cc.minCoins);
    }
}
