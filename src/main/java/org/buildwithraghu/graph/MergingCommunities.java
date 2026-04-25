package org.buildwithraghu.graph;

public class MergingCommunities {

    static class UnionFind {
        int[] parent, size;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY)
                return;

            if (size[parentX] > size[parentY]) {
                parent[parentY] = parentX;
                size[parentX] += size[parentY];
            } else {
                parent[parentX] = parentY;
                size[parentY] += size[parentX];
            }
        }

        public int find(int x) {
            if (x == parent[x])
                return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }
}
