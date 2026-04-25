package org.buildwithraghu.graph;

public class NumberOfIslands {

    // https://leetcode.com/problems/number-of-islands
    class UnionFind {
        int[] parent;
        int[] rank;
        int count = 0;

        UnionFind(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            count = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * n + j;
                        parent[id] = id;
                        count++; // initially each land cell is its own island
                    }
                }
            }
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY)
                return;
            // union by rank
            if (rank[parentX] > rank[parentY]) {
                parent[parentY] = parentX;
            } else if (rank[parentX] < rank[parentY]) {
                parent[parentX] = parentY;
            } else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }
            count--;
        }

        int find(int x) {
            if (parent[x] == x)
                return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        int getCount() {
            return count;
        }
    }

    public int numIslands_dsu(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // mark visited to prevent duplicate unions
                    grid[i][j] = '0';
                    for (int[] d : dirs) {
                        int x = i + d[0], y = j + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            uf.union(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }
        return uf.getCount();
    }

    // much faster
    public static void dfs(char[][] matrix, int i, int j, int m, int n) {
        if (i >= m || j >= n || i < 0 || j < 0 || matrix[i][j] == '0')
            return;
        matrix[i][j] = '0';
        dfs(matrix, i-1, j, m, n);
        dfs(matrix, i, j-1, m, n);
        dfs(matrix, i+1, j, m, n);
        dfs(matrix, i, j+1, m, n);
    }

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int c = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    c++;
                }
            }
        }
        return c;
    }
}
