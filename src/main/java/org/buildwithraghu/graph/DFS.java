package org.buildwithraghu.graph;

import java.util.*;
import java.util.stream.IntStream;

public class DFS {

    private ArrayList<Integer>[] graph;

    @SuppressWarnings("unchecked")
    public DFS(int N) {
        graph = (ArrayList<Integer>[]) new ArrayList[N + 1];
        IntStream.range(0, N+1).forEach(i -> graph[i] = new ArrayList<>());
    }

    public void addEdge(int u, int v, int c) {
        graph[u].add(v);
    }

    public void dfs(int u, boolean[] visited) {
        if (visited[u])
            return;
        visited[u] = true;
        for(int v: graph[u]) {
            System.out.println(u + " -> " + v);
            dfs(v, visited);
        }
    }

    public static void main(String[] args) {
        DFS tree = new DFS(6);
        tree.addEdge(1, 2, 0);
        tree.addEdge(1, 3, 0);
        tree.addEdge(1, 4, 0);
        tree.addEdge(2, 6, 0);
        boolean[] visited = new boolean[7];
        Arrays.fill(visited, false);
        tree.dfs(1, visited);
    }
}
