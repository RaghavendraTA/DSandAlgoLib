package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class OptimumJumpsToReachLast {

    static int jump(int[] A) {
        int n = A.length;
        int[] result = new int[n];

        if (n == 0 || A[0] == 0)
            return Integer.MAX_VALUE;

        result[0] = 0;

        for(int i = 0; i < n; i++) {
            result[i] = Integer.MAX_VALUE;
            for(int j = 0; j < i; j++) {
                if (A[i] >= (i-j)) {
                    result[i] = Math.max(result[i], result[j] + 1);
                }
            }
        }

        return result[n-1];
    }
}
