package dynamicprogramming.subsetsum;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Arrays;

public class isSubsetHalfSum {

    static boolean isSubsetHalfSum(int[] A) {

        int k = 0, n = A.length;
        for(int i = 0; i < n; i++) {
            k += A[i];
        }
        boolean[] t = new boolean[k + 1];
        Arrays.fill(t, false);
        t[0] = true;
        for(int i = 0; i < n; i++) {
            for(int j = k - A[i]; j >= 0; j--) {
                if (t[j]) {
                    t[j + A[i]] = true;
                }
            }
        }
        return t[k/2];
    }

    public static void main(String[] args) {
        System.out.println(isSubsetHalfSum(new int[]{1, 6, 3, 8, 2}));
    }
}
