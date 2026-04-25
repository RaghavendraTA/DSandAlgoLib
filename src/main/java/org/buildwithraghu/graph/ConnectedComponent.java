package org.buildwithraghu.graph;

public class ConnectedComponent {

    public static void dfs(int[][] matrix, int i, int j, int m, int n) {
        if (i >= m || j >= n || i < 0 || j < 0 || matrix[i][j] == 0)
            return;
        matrix[i][j] = 0;
        dfs(matrix, i-1, j, m, n);
        dfs(matrix, i, j-1, m, n);
        dfs(matrix, i+1, j, m, n);
        dfs(matrix, i, j+1, m, n);
    }

    public static int connectedComponent(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int c = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dfs(matrix, i, j, m, n);
                    c++;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 1, 1}
        };
        System.out.println(connectedComponent(mat));
    }
}
