package org.buildwithraghu.utilities;

import java.util.*;

public class DirectedGraph<T> {

    private Map<T, List<Triplets<T>>> adj = new HashMap<>();

    public void addEdge(T from, T to) {
        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(new Triplets<>(from, to, 0));
    }

    public void addWeightedEdge(T from, T to, int cost) {
        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(new Triplets<>(from, to, cost));
    }

    public List<Triplets<T>> getAdjacentNodes(T node) {
        return adj.getOrDefault(node, Collections.emptyList());
    }

    public Set<T> getNodes() {
        return adj.keySet();
    }

    public static class Triplets<T> implements Comparable<Triplets<T>> {
        public T u, node;
        public int cost;
        public T v;

        public Triplets(T u, T node, int cost) {
            this.u = u;
            this.node = node;
            this.cost = cost;
            this.v = node;
        }

        @Override
        public int compareTo(Triplets<T> other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
