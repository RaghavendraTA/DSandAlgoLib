package greedy;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class BestTimeToBuyAndSellAStock {

    public static int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    // Maximum profit by buying and selling a share at most twice
    public static int maxProfitByTwoTransaction() {
        return 0;
    }

    public static void main(String[] args) {
        int[] prices = {10, 22, 5, 75, 65, 80};
        System.out.println(maxProfit(prices));
    }
}
