package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 30-Jun-2021
 */

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static int largestCount = 0;

    public static void myMethod(int counter, int prev, int idx, int[] arr) {
        if (idx >= arr.length) {
            largestCount = Math.max(largestCount, counter);
        }
        for(int i = idx; i < arr.length; i++) {
            if (arr[i] > prev) {
                myMethod(counter + 1, arr[i], i + 1, arr);
            }
        }
        largestCount = Math.max(largestCount, counter);
    }

    public static void usingDP(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1)
                    dp[i] = dp[j] + 1;
                maxSum = Math.max(maxSum, dp[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 1, 2, 3};
        myMethod(0, Integer.MIN_VALUE, 0, arr);
        System.out.println(largestCount);
        usingDP(arr);
    }

}
