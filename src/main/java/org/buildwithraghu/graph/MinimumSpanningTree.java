package graph.paths;

/*
 * created by raghavendra.ta on 22-Aug-2021
 */

import utilities.DirectedGraph.Triplets;
import utilities.UnDirectedGraph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MinimumSpanningTree {

    public static <T> void primsMinimumSpanningTree(T start, UnDirectedGraph<T> graph) {

        PriorityQueue<Triplets<T>> queue = graph.getHeapQueueWithCostSupport();
        Set<T> visited = new HashSet<>();

        queue.add(new Triplets<>(start, start, 0));

        while(!queue.isEmpty()) {
            Triplets<T> node = queue.poll();

            if (visited.contains(node.v)) continue;
            visited.add(node.v);

            if (node.v != start)
                System.out.println(node.u + " -> " + node.v + " (" + node.cost + ")");

            graph.getAdjacentNodes(node.v).forEach(t -> queue.add(new Triplets<>(node.v, t.node, t.cost)));
        }
    }

    public static void main(String[] args) {

        UnDirectedGraph<Character> graph = new UnDirectedGraph<>();

        graph.addLine('A', 'B', 2);
        graph.addLine('A', 'C', 3);
        graph.addLine('B', 'C', 2);
        graph.addLine('B', 'E', 5);
        graph.addLine('C', 'D', 1);
        graph.addLine('C', 'E', 1);

        UnDirectedGraph<Integer> graph2 = new UnDirectedGraph<>();

        graph2.addLine(0, 1, 4);
        graph2.addLine(0, 7, 8);
        graph2.addLine(1, 7, 11);
        graph2.addLine(1, 2, 8);
        graph2.addLine(2, 8, 2);
        graph2.addLine(2, 5, 4);
        graph2.addLine(2, 3, 7);
        graph2.addLine(3, 5, 14);
        graph2.addLine(3, 4, 9);
        graph2.addLine(4, 5, 10);
        graph2.addLine(5, 6, 2);
        graph2.addLine(6, 8, 6);
        graph2.addLine(6, 7, 1);
        graph2.addLine(7, 8, 7);

        MinimumSpanningTree.primsMinimumSpanningTree(0, graph2);
    }
}
