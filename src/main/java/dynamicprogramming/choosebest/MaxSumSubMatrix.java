package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class MaxSumSubMatrix {

    public void findMaxSubMatrix(int[][] A) {
        int n = A.length;
        int[][] mat = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (j == 0) {
                    mat[j][i] = A[j][i];
                } else {
                    mat[j][i] = A[j][i] + mat[j-1][i];
                }
            }
        }

        int maxSoFar = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int min = 0;
                int subMatrix = 0;
                for(int k = 0; k < n; k++) {
                    if (i == 0)
                        subMatrix += mat[j][k];
                    else
                        subMatrix += mat[j][k] - mat[i-1][k];
                }
                if (subMatrix < min)
                    min = subMatrix;
                if (subMatrix - min > maxSoFar)
                    maxSoFar = subMatrix - min;
            }
        }

        System.out.println(maxSoFar);
    }
}
