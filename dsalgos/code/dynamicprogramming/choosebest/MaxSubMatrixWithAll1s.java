package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class MaxSubMatrixWithAll1s {

    /**
     *
     * Easy solution is:
     *  1. copy 1st row and 1st column as is
     *  2. if (mat[i][j] == 1)
     *  3.      dp[i][j] = min {all previous 3 directions} + 1
     *  4. else
     *  5.      dp[i][j] = 0
     *  6. Find the max number in matrix that's the answer
     *
     *
     * construct a sum matrix dp[m][n] for the given matrix mat[m][n]
     *      copy first row and first columns as it from mat[][] to dp[][]
     *      for other entries, use the following expression to construct dp[][]
     *          if (mat[i][j])
     *              dp[i][j] = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) + 1
     *          else
     *              dp[i][j] = 0;
     * Find the max entry in dp[m][n]
     * using the value and coordinates of max entry in dp[i] print sub matrix dp[][]
     */

    public static void findSubMatrixWithAll1s(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++)
            dp[i][0] = mat[i][0];

        for(int j = 0; j < n; j++)
            dp[0][j] = mat[0][j];

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if (mat[i][j] == 1)
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                else
                    dp[i][j] = 0;
            }
        }

        int maxOfS = dp[0][0];
        int mat_i = 0, mat_j = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (dp[i][j] > maxOfS) {
                    maxOfS = dp[i][j];
                    mat_i = i;
                    mat_j = j;
                }
            }
        }

        // printing the matrix
        for(int i = mat_i; i > mat_i - maxOfS; i--) {
            for(int j = mat_j; j > mat_j - maxOfS; j--) {
                System.out.println(mat[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
