package graph.paths;

/*
 * created by raghavendra.ta on 14-Jul-2021
 */

import java.util.*;

public class DetectCycle {

    static int[] predecessor;
    static Set<Integer> visited = new HashSet<>();

    static boolean detectCycle(int V, Map<Integer, List<Integer>> graph) {
        for(int i = 0; i < V; i++) {
            predecessor[i] = 0;
        }
        for(int i = 0; i < V; i++) {
            if (!visited.contains(i) && hasCycle(V, graph, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycle(int V, Map<Integer, List<Integer>> graph, int u) {
        visited.add(u);
        for(int i = 0; i < V; i++) {
            if (graph.getOrDefault(u, Collections.emptyList()).contains(i)) {
                if (predecessor[i] != u && visited.contains(i)) {
                    return true;
                }
                else {
                    predecessor[i] = u;
                    return hasCycle(V, graph, i);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Collections.singletonList(2));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Collections.singletonList(3));
        int noOfVertices = 4;
        predecessor = new int[noOfVertices + 1];
        boolean check = detectCycle(noOfVertices, graph);
        System.out.println(check);

        // This graph doesn't contains cycle
        graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Collections.singletonList(3));
        noOfVertices = 3;
        predecessor = new int[noOfVertices + 1];
        check = detectCycle(noOfVertices, graph);
        System.out.println(check);
    }
}
