package dynamicprogramming.subsetsum;

/*
 * created by raghavendra.ta on 30-Jun-2021
 */

public class isSubsetSum {

    public static boolean isSubsetSumUsingRecursion(int[] arr, int n, int sum) {

        if (sum == 0)
            return true;
        if (n == 0)
            return false;
        if (arr[n - 1] > sum)
            return isSubsetSumUsingRecursion(arr, n - 1, sum);

        return isSubsetSumUsingRecursion(arr, n - 1, sum) || isSubsetSumUsingRecursion(arr, n - 1, sum - arr[n - 1]);
    }

    public static void isSubsetSum(int[] arr, int k) {

        boolean[][] dp = new boolean[k + 1][arr.length + 1];

        for (int i = 1; i <= k; i++)
            dp[i][0] = false;

        for (int j = 0; j <= arr.length; j++)
            dp[0][j] = true;

        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= arr.length; j++) {
                dp[i][j] = dp[i][j - 1];
                if (i >= arr[j - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - arr[j - 1]][j - 1];
                }
            }
        }

        System.out.println(dp[k][arr.length]);
    }

    public static void main(String[] args) {

        isSubsetSum(new int[]{3, 2, 4, 19, 3, 7, 13, 10, 6, 11}, 1);
        boolean ans = isSubsetSumUsingRecursion(new int[]{3, 2, 4, 19, 3, 7, 13, 10, 6, 11},10, 1);
        System.out.println(ans);
    }

}
