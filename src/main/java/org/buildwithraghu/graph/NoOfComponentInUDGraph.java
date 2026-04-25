package org.buildwithraghu.graph;

import java.util.*;

public class NoOfComponentInUDGraph {

    // https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
    public int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e: edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);
        int count = 0;
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, graph, visited);
            }
        }
        return count;
    }

    private void dfs(int start, List<Integer>[] graph, boolean[] visited) {
        if (visited[start]) return;
        visited[start] = true;
        for(Integer e: graph[start]) {
            dfs(e, graph, visited);
        }
    }

    // -------------------------------------------------------------------------------------------------
    // Someone solved using Union-Find
    public int countComponents_UF(int n, int[][] edges) {
        UF myUF = new UF(n);
        for (int[] e : edges) {
            myUF.union(e[0], e[1]);
        }
        return myUF.getCount();
    }

    class UF {
        int count;
        int[] parent;

        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            this.count = n;
        }

        int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int p, int q) {
            int parentP = find(p);
            int parentQ = find(q);
            if (parentP == parentQ) {
                return;
            }
            parent[parentP] = parentQ;
            this.count--;
        }

        int getCount() {
            return this.count;
        }
    }
}
