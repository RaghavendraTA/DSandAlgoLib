package org.buildwithraghu.backtracking;

public class ZeroStripping {

    private static final int MAX = 9999;

    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0)
                    fillZeros(matrix, i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == MAX) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    private void fillZeros(int[][] matrix, int i, int j) {
        // if the matrix already has 0 in edge cell will not get consider
        // this we set the strip to max instead, will pick it later to set everything to zero
        int m = matrix.length, n = matrix[0].length;
        for (int c = 0; c < m; c++) {
            if (matrix[c][j] != 0)
                matrix[c][j] = MAX;
        }
        for (int r = 0; r < n; r++) {
            if (matrix[i][r] != 0)
                matrix[i][r] = MAX;
        }
    }
}