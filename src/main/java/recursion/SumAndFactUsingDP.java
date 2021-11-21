package recursion;

/*
 * created by raghavendra.ta on 20-Nov-2021
 */


import utilities.TestSuite;

public class SumAndFactUsingDP extends TestSuite {

    // Recursion
    int sumOfN(int n) {

        if (n == 1) {
            return 1;
        }

        return n + sumOfN(n - 1);
    }

    // Dynamic Programming
    int[] sumOfNUsingDp(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + i;
        }
        return dp;
    }

    // Recursion
    int fact(int n) {

        if (n == 1) {
            return 1;
        }
        return n * fact(n - 1);
    }

    // Dynamic Programming
    int[] factUsingDP(int n) {

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * i;
        }
        return dp;
    }

    @Override
    public void test() {
        int[] dp = sumOfNUsingDp(10);

        for (int i = 1; i <= 10; i++) {
            assertEquals(dp[i], sumOfN(i));
        }

        dp = factUsingDP(10);
        for (int i = 1; i <= 10; i++) {
            assertEquals(dp[i], fact(i));
        }
    }

    public static void main(String[] args) {

        new SumAndFactUsingDP().main();
    }
}
