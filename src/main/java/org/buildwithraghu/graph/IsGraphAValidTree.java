package org.buildwithraghu.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IsGraphAValidTree {

    public boolean validTree_dfs(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for(int[] e: edges) {
            graph[e[0]].add((e[1]));
            graph[e[1]].add((e[0]));
        }
        HashSet<Integer> visited = new HashSet<>();
        if (!dfs(graph, 0, visited, -1)) {
            return false;
        }
        return visited.size() == n;
    }

    boolean dfs(List<Integer>[] graph, int u, HashSet<Integer> visited, int parent) {
        if (visited.contains(u)) {
            return false;
        }
        visited.add(u);
        for(int v: graph[u]) {
            if (v == parent) continue;
            if (!dfs(graph, v, visited, u)) return false;
        }
        return true;
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n-1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        // Add each edge. Check if a merge happened, because if it
        // didn't, there must be a cycle.
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (!uf.union(u, v)) {
                return false;
            }
        }

        // If we got this far, there's no cycles!
        return true;
    }

    class UnionFind {

        private final int[] parent;
        private final int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int A) {
            int root = A;
            while (parent[root] != root) {
                root = parent[root];
            }
            // Step 2: Do a second traversal, this time setting each node to point
            // directly at A as we go.
            while (A != root) {
                int oldRoot = parent[A];
                parent[A] = root;
                A = oldRoot;
            }
            return root;
        }

        public boolean union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX == parentY) {
                return false; // cycle detected
            }
            // merge the smaller tree into the larger (Union by Size optimization).
            if (size[parentX] < size[parentY]) {
                parent[parentX] = parentY;
                size[parentY] += size[parentX];
            } else {
                parent[parentY] = parentX;
                size[parentX] += size[parentY];
            }
            return true;
        }
    }
}
