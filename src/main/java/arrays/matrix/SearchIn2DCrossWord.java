package arrays.matrix;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class SearchIn2DCrossWord {

    static int R, C;

    static int[] x = {-1, -1, -1, 0, 0, 1, 1, 1};

    static int[] y = {-1, 0, 1, -1, 1, -1, 0, 1};

    static boolean search2D(char[][] grid, int row, int col, String word) {

        if (grid[row][col] != word.charAt(0)) {
            return false;
        }

        int len = word.length();

        for (int dir = 0; dir < 8; dir++) {

            int k, rd = row + x[dir], cd = col + y[dir];

            for (k = 1; k < len; k++) {

                if (rd >= R || rd < 0 || cd >= C || cd < 0) {
                    break;
                }

                if (grid[rd][cd] != word.charAt(k)) {
                    break;
                }

                rd += x[dir];
                cd += y[dir];
            }

            if (k == len) {
                return true;
            }
        }
        return false;
    }

    static void patternSearch(char[][] grid, String word) {

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (search2D(grid, row, col, word)) {
                    System.out.println(
                            "pattern found at " + row + ", " + col);
                }
            }
        }
    }
}
