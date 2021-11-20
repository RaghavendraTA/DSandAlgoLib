package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 02-Jul-2021
 */

/**               _
 *               |   cd + T(i - 1, j)       |
 *               |   ci + T(i, j-1)         }   if A[i] != B[i]
 * T(i, j) = min {   cr + T(i - 1, j - 1)   |
 *               |   T(i - 1, j - 1)            if A[i] == B[j]
 *                -
 *
 */
public class EditDistance {

    public static void edit(String A, String B) {

        int m = A.length(), n = B.length();
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else if (A.charAt(i) == B.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }

        System.out.println(dp[m - 1][n - 1]);
    }

    public static void main(String[] args) {

        edit("cat", "cutting");
    }

}
