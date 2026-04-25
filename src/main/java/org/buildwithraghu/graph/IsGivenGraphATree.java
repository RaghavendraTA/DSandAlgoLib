package org.buildwithraghu.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IsGivenGraphATree {

    // https://leetcode.com/problems/graph-valid-tree/
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e: edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        HashSet<Integer> visited = new HashSet<>();
        if (!dfs(graph, visited, 0, -1))
            return false;
        return visited.size() == n;
    }

    private boolean dfs(List<Integer>[] graph, HashSet<Integer> visited, int u, int parent) {
        if (visited.contains(u))
            return false;
        visited.add(u);
        for(int v: graph[u]) {
            if (v == parent) continue; // skip the edge we came from as its undirected graph
            if (!dfs(graph, visited, v, u)) return false;
        }
        return true;
    }
}
