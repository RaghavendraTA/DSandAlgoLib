package org.buildwithraghu.graph;

import java.util.*;

public class Dijkstra {

    static class Pair {
        int vertex, weight;
        Pair(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void addEdge(List<Pair>[] graph, int u, int v, int c) {
        graph[u].add(new Pair(v, c));
        graph[v].add(new Pair(u, c));
    }

    public static int[] singleSourceShortestPaths(List<Pair>[] graph, int start, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));

        pq.add(new Pair(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int u = pq.poll().vertex;
            for(Pair p: graph[u]) {
                int v = p.vertex, w = p.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 6;
        List<Pair>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 1);
        addEdge(graph, 0, 2, 4);
        addEdge(graph, 1, 2, 2);
        addEdge(graph, 1, 3, 5);
        addEdge(graph, 2, 3, 1);
        addEdge(graph, 3, 4, 3);
        addEdge(graph, 4, 5, 1);

        int[] dist = singleSourceShortestPaths(graph, 0, V);
        for(int i = 1; i < V; i++) {
            System.out.println("0 -> " + i + " = " + dist[i]);
        }
    }
}
