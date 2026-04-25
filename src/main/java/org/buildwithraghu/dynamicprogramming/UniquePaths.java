package org.buildwithraghu.dynamicprogramming;

public class UniquePaths {

    // https://leetcode.com/problems/unique-paths/
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    // https://leetcode.com/problems/unique-paths-ii/
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        if (grid[0][0] == 1) return 0;
        grid[0][0] = 1;

        for(int i = 0; i < m; i++)
            grid[i][0] = (grid[i][0] == 0 && grid[i-1][0] == 1) ? 1 : 0;

        for (int j = 1; j < n; j++)
            grid[0][j] = (grid[0][j] == 0 && grid[0][j - 1] == 1) ? 1 : 0;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0)
                    grid[i][j] = grid[i-1][j] + grid[i][j-1];
                else
                    grid[i][j] = 0;
            }
        }

        return grid[m-1][n-1];
    }
}
