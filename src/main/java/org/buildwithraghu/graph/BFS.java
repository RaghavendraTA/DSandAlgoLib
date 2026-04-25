package org.buildwithraghu.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

public class BFS {

    private ArrayList<Integer>[] graph;

    @SuppressWarnings("unchecked")
    public BFS(int N) {
        graph = (ArrayList<Integer>[]) new ArrayList[N + 1];
        IntStream.range(0, N+1).forEach(i -> graph[i] = new ArrayList<>());
    }

    public void addEdge(int u, int v, int c) {
        graph[u].add(v);
    }

    public void bfs(int start, int N) {
        boolean[] visited = new boolean[N+1];
        Arrays.fill(visited, false);

        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(start);
        visited[start] = true;

        while(!dq.isEmpty()) {
            int u = dq.peekFirst();
            dq.pollFirst();
            for(int v: graph[u]) {
                System.out.println(u + " -> " + v);
                if (!visited[v]) {
                    dq.addLast(v);
                    visited[v] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS search = new BFS(6);
        search.addEdge(1, 2, 0);
        search.addEdge(1, 3, 0);
        search.addEdge(1, 4, 0);
        search.addEdge(2, 6, 0);
        search.bfs(1, 6);
    }
}
