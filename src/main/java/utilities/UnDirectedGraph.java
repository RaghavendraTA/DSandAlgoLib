package utilities;

import java.util.*;

public class UnDirectedGraph<T> {

    private Map<T, List<DirectedGraph.Triplets<T>>> adj = new HashMap<>();

    public void addLine(T from, T to, int cost) {
        adj.computeIfAbsent(from, k -> new ArrayList<>()).add(new DirectedGraph.Triplets<>(from, to, cost));
        adj.computeIfAbsent(to, k -> new ArrayList<>()).add(new DirectedGraph.Triplets<>(to, from, cost));
    }

    public List<DirectedGraph.Triplets<T>> getAdjacentNodes(T node) {
        return adj.getOrDefault(node, Collections.emptyList());
    }

    public PriorityQueue<DirectedGraph.Triplets<T>> getHeapQueueWithCostSupport() {
        return new PriorityQueue<>();
    }
}
