package dynamicprogramming.subsetsum;

/*
 * created by raghavendra.ta on 27-Jun-2021
 */

/**
 * Question: solve summation(2 x T(i) x T(i-1) for n > 1)
 * where 'i' iterates from 1 to n-1
 * Given: T(0) = T(1) = 2
 */

public class SolveSummation {

    public static long recursion(int n) {
        if (n == 0 || n == 1)
            return 2;
        long sum = 0;
        for(int i = 1; i < n; i++)
            sum += 2 * recursion(i) * recursion(i-1);
        return sum;
    }

    public static long usingDP(int n) {
        long[] dp = new long[n+1];
        dp[0] = 2;
        dp[1] = 2;
        for(int i = 2; i <= n; i++) {
            dp[i] = 0;
            for(int j = 1; j < i; j++)
                dp[i] += 2 * dp[j] * dp[j-1];
        }
        return dp[n];
    }

    public static long usingDPWithSingleIteration(int n) {
        long[] dp = new long[n+1];
        dp[0] = dp[1] = 2;
        dp[2] = 8;
        for(int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i-1] * dp[i-2]) + dp[i - 1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        for(int i = 2; i <= 8; i++) {
            System.out.print("recursion(" + i + ") => " + recursion(i) + ", ");
            System.out.println("DP(" + i + ") => " + usingDPWithSingleIteration(i) + ".");
        }
    }
}
