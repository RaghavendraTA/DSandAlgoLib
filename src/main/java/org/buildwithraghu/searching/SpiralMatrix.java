package arrays.matrix;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    List<Integer> spiralOrder(int[][] matrix) {

        int k = 0, i = 0, j = 0;
        int m = matrix.length, n = matrix[0].length;

        int start_row = 0, end_row = m - 1, start_col = 0, end_col = n - 1;
        List<Integer> traverse = new ArrayList<>();

        while(start_row <= end_row && start_col <= end_col) {
            traverse.add(matrix[i][j]);

            if (k == 0 && j + 1 > end_col) { k = (k + 1) % 4; start_row++; }
            if (k == 1 && i + 1 > end_row) { k = (k + 1) % 4; end_col--; }
            if (k == 2 && j - 1 < start_col) { k = (k + 1) % 4; end_row--; }
            if (k == 3 && i - 1 < start_row) { k = 0; start_col++; }

            switch (k) {
                case 0 -> j++;
                case 1 -> i++;
                case 2 -> j--;
                default -> i--;
            }
        }

        return traverse;
    }
}
