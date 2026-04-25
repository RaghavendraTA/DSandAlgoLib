package org.buildwithraghu.graph;

public class NoOfOpToMakeNetworkConnected {

    // https://leetcode.com/problems/number-of-operations-to-make-network-connected

    static class UnionFind {
        int[] parent;
        int e = 0, N;

        UnionFind(int n) {
            N = n;
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY) {
                e++;
                return;
            }

            parent[parentX] = parentY;
        }

        int find(int x) {
            if (parent[x] == x)
                return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        int canConnect() {
            int u = 0;
            for(int i = 0; i < N; i++) {
                if (parent[i] == i) {
                    u++;
                }
            }
            return (e >= u-1) ? (u-1) : -1;
        }
    }

    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        for(int[] pair: connections) {
            uf.union(pair[0], pair[1]);
        }
        return uf.canConnect();
    }
}
