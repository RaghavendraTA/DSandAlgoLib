package utilities;

/*
 * created by raghavendra.ta on 02-Nov-2021
 */

import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph<T> {

    public static class Pair<T> {

        public T node;
        public long cost;

        public Pair(final T node, final long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return this.node + "(" + this.cost + ")";
        }
    }

    public static class Triplets<T> {

        public T u;
        public T v;
        public long cost;

        public Triplets(final T u, final T v, final long cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    protected Map<T, List<Pair<T>>> nodes;

    DirectedGraph() { this.nodes = new HashMap<>(); }

    public void addLine(T u, T v) {
        this.addLine(u, v, 1L);
    }

    public void addLine(T u, T v, long cost) {
        nodes.putIfAbsent(u, new ArrayList<>());
        nodes.get(u).add(new Pair<>(v, cost));
    }

    public List<Pair<T>> getAdjacentNodes(T u) {
        return nodes.get(u);
    }

    public List<T> getAdjacentNodesWithoutCost(T u) {
        return nodes.get(u).stream().map(n -> n.node).collect(Collectors.toList());
    }

    public PriorityQueue<Triplets<T>> getHeapQueueWithCostSupport() {
        return new PriorityQueue<>(Comparator.comparingLong(t -> t.cost));
    }

    public PriorityQueue<T> getHeapQueueWithoutCostSupport() {
        return new PriorityQueue<>();
    }
}