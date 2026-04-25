package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Arrays;

/**              | 0                        if j == 0
 *               | 1                        if i == 0
 * L(i, j) = max { L(i-1, j-1) + L(i, j-1)  if S[i] == T[j]
 *               | L(i-1, j)                if S[i] != T[j]
 */
public class IsStringSInT {

    static void isStringSInT(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 0;
                } else if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for(int[] arr: dp)
            System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        String s = "aba", t = "abadcb";
        isStringSInT(s, t);
    }
}
