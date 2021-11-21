package graph.paths;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.*;


/**
 * Video ref: https://www.youtube.com/watch?v=Q9PIxaNGnig
 */
public class TopologicalSort {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void insertEdge(Integer u, Integer v) {

        graph.computeIfAbsent(u, k -> new ArrayList<>());
        graph.get(u).add(v);
    }

    public static void topologicalSort(int vertex, Set<Integer> visited, Stack<Integer> stack) {

        visited.add(vertex);

        for (Integer i : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(i)) {
                topologicalSort(i, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public static void topologicalSort(int vertices) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                topologicalSort(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ", ");
        }
    }

    public static void main(String[] args) {
        insertEdge(5, 2);
        insertEdge(5, 0);
        insertEdge(4, 0);
        insertEdge(4, 1);
        insertEdge(2, 3);
        insertEdge(3, 1);

        topologicalSort(6);
    }
}
