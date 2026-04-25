package recursion;

/*
 * created by raghavendra.ta on 21-Nov-2021
 */

import utilities.TestSuite;

public class FibonacciSeries extends TestSuite {

    // recursion
    public int fibRec(int n) {

        if (n == 0 || n == 1) {
            return n;
        }

        return fibRec(n - 1) + fibRec(n - 2);
    }

    // DP
    public int[] fibDP(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp;
    }

    @Override
    public void test() {
        int n = 10;
        int[] dp = fibDP(n);
        assertEquals(fibRec(n), dp[n]);
    }

    public static void main(String[] args) {

        new FibonacciSeries().main();
    }
}
