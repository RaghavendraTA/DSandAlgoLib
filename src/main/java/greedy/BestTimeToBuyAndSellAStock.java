package greedy;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class BestTimeToBuyAndSellAStock {

    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}