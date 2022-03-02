package dynamicprogramming.maxsubarray;

/*
 * created by raghavendra.ta on 27-Jun-2021
 */

public class MaxValContiguousSubArray {

    public static int solvingUsingDP(int[] a) {
        int[] sum = new int[a.length];
        sum[0] = a[0];
        int maxSum = sum[0];
        for(int i = 1; i < a.length; i++) {
            if (sum[i - 1] + a[i] > a[i]) {
                sum[i] = sum[i - 1] + a[i];
            } else {
                sum[i] = Math.max(sum[i - 1], a[i]);
            }
            maxSum = Math.max(maxSum, sum[i]);
        }
        return maxSum;
    }

    // solveWithoutAdditionalSpace
    public static long kadanesAlgo(int[] arr) {
        long sum = arr[0], maxSum = arr[0];
        for(int i = 1; i < arr.length; i++) {
            sum = Math.max(arr[i], arr[i] + sum);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 11, -4, 13, -5, 2};
        //int[] arr = new int[]{1, -3, 4, -2, -1, 6};
        System.out.println(solvingUsingDP(arr));
        System.out.println(kadanesAlgo(arr));
    }
}
