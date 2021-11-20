package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 14-Jul-2021
 */

/**
 *
 *          |                                                                   |
 *   max    {       | v(i+1, j-1)|                 | v(i, j-2)       |          }
 *          |  min  | v(i + 2, j |  + vi,      min | v(i + 1, j - 1) | + vi     |
 *
 *
 * F(i, j) represents the maximum value the user
 * can collect from i'th coin to j'th coin.
 *
 * F(i, j) = Max(Vi + min(F(i+2, j), F(i+1, j-1) ),
 *               Vj + min(F(i+1, j-1), F(i, j-2) ))
 * As user wants to maximise the number of coins.
 *
 * Base Cases
 *     F(i, j) = Vi           If j == i
 *     F(i, j) = max(Vi, Vj)  If j == i + 1
 */
public class optimalStrategyForAGame {

    static int optimalStrategyOfGame(int arr[], int n)
    {
        // Create a table to store
        // solutions of subproblems
        int table[][] = new int[n][n];
        int idx, i, j, x, y, z;

        // Fill table using above recursive formula.
        // Note that the tableis filled in diagonal
        // fashion (similar to http:// goo.gl/PQqoS),
        // from diagonal elements to table[0][n-1]
        // which is the result.
        for (idx = 0; idx < n; ++idx) {
            for (i = 0, j = idx; j < n; ++i, ++j) {

                // Here x is value of F(i+2, j),
                // y is F(i+1, j-1) and z is
                // F(i, j-2) in above recursive formula
                x = ((i + 2) <= j) ? table[i + 2][j] : 0;

                y = ((i + 1) <= (j - 1)) ? table[i + 1][j - 1] : 0;

                z = (i <= (j - 2)) ? table[i][j - 2] : 0;

                table[i][j] = Math.max(arr[i] + Math.min(x, y), arr[j] + Math.min(y, z));
            }
        }

        return table[0][n - 1];
    }
}
