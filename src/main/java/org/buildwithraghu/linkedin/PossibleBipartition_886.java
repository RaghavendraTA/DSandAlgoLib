package org.buildwithraghu.linkedin;

import java.util.*;

public class PossibleBipartition_886 {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n+1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] p: dislikes) {
            graph.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
            graph.computeIfAbsent(p[1], k -> new ArrayList<>()).add(p[0]);
        }
        for(int i = 1; i <= n; i++) {
            if (color[i] == 0 && !bfs(graph, color, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(Map<Integer, List<Integer>> graph, int[] color, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;

        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(Integer v: graph.getOrDefault(u, Collections.emptyList())) {
                if (color[v] == color[u])
                    return false;
                if (color[v] == 0)
                    queue.offer(v);
                color[v] = -color[u];
            }
        }
        return true;
    }

    // Let's Try union Find method as well
    static class UnionFindForBipartition {

        int[] parent;
        int[] rank;

        public UnionFindForBipartition(int size) {
            parent = new int[size];
            for(int i = 0; i < size; i++) {
                parent[i] = i;
            }
            rank = new int[size];
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int parentX = find(x), parentY = find(y);
            if (parentX == parentY) {
                return;
            } else if (rank[parentX] < rank[parentY]) {
                parent[parentX] = parentY;
            } else if (rank[parentX] > rank[parentY]) {
                parent[parentY] = parentX;
            } else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }
        }

    }

    public boolean possibleBipartition_uf(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] p: dislikes) {
            graph.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
            graph.computeIfAbsent(p[1], k -> new ArrayList<>()).add(p[0]);
        }
        UnionFindForBipartition dsu = new UnionFindForBipartition(n + 1);
        for (int node = 1; node <= n; node++) {
            if (!graph.containsKey(node))
                continue;
            for(int neigh: graph.get(node)) {
                if (dsu.find(node) == dsu.find(neigh)) {
                    return false;
                }
                dsu.union(graph.get(node).get(0), neigh);
            }
        }
        return true;
    }
}
