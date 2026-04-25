package org.buildwithraghu.graph;

public class NoOfIslands {

    // https://leetcode.com/problems/number-of-islands/
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, c = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == '0')
                    continue;
                dfs(grid, i, j, m, n);
                c++;
            }
        }
        return c;
    }

    void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i >= m || j >= n || j < 0 || i < 0 || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        dfs(grid, i+1, j, m, n);
        dfs(grid, i-1, j, m, n);
        dfs(grid, i, j+1, m, n);
        dfs(grid, i, j-1, m, n);
    }
}
