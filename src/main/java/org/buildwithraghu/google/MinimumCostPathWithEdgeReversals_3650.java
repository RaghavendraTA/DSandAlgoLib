package org.buildwithraghu.google;

import java.util.*;

public class MinimumCostPathWithEdgeReversals_3650 {

    static class Edge {
        int v, w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    Map<Integer, List<Edge>> graph;

    public int minCost(int n, int[][] edges) {
        graph = new HashMap<>();
        for(int[] edge: edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new Edge(edge[1], edge[2]));
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new Edge(edge[0], -edge[2]));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.w));
        pq.add(new Edge(0, 0));

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Edge u = pq.poll();
            for(Edge v: graph.getOrDefault(u.v, Collections.emptyList())) {
                int w = (v.w < 0 ? (2 * -v.w) : v.w);
                if (dist[u.v] + w < dist[v.v]) {
                    dist[v.v] = dist[u.v] + w;
                    pq.add(new Edge(v.v, dist[v.v]));
                }
            }
        }
        return dist[n-1];
    }
}
