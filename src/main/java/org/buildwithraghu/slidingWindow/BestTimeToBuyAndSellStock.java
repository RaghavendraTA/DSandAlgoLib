package org.buildwithraghu.slidingWindow;

public class BestTimeToBuyAndSellStock {

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock
    public int maxProfit(int[] prices) {
        int minPrice = prices[0], maxProfit = 0;
        for(int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1])
                minPrice = Math.min(minPrice, prices[i]);
            else
                maxProfit = Math.max(maxProfit, prices[i]-minPrice);
        }
        return maxProfit;
    }
}
