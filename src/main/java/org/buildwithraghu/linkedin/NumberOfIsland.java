package org.buildwithraghu.linkedin;

public class NumberOfIsland {

    static class UnionFindNumberOfIsland {
        int[] parent;
        int[] rank;
        public int total;

        UnionFindNumberOfIsland(int n) {
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
            rank = new int[n];
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int parentX = find(x), parentY = find(y);
            if (parentX == parentY)
                return;
            if (rank[parentX] < rank[parentY]) {
                parent[parentX] = parentY;
            } else if (rank[parentX] > rank[parentY]) {
                parent[parentY] = parentX;
            } else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }
            total--;
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, nodes = 0;
        UnionFindNumberOfIsland uf = new UnionFindNumberOfIsland(m * n);

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0')
                    continue;
                else
                    uf.total++;
                int y, x = i * n + j;
                if (i < m-1 && grid[i+1][j] == '1') {
                    y = (i + 1) * n + j;
                    uf.union(x, y);
                }
                if (i > 0 && grid[i-1][j] == '1') {
                    y = (i - 1) * n + j;
                    uf.union(x, y);
                }
                if (j < n-1 && grid[i][j+1] == '1') {
                    y = i * n + (j + 1);
                    uf.union(x, y);
                }
                if (j > 0 && grid[i][j-1] == '1') {
                    y = i * n + (j - 1);
                    uf.union(x, y);
                }
            }
        }
        return uf.total;
    }
}
