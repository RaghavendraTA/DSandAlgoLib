package leetcode.graph;

/*
 * created by raghavendra.ta on 16-Jul-2021
 */

import java.util.*;

public class StronglyConnectedComponent {

    private final Map<Integer, List<Integer>> graph = new HashMap<>();

    private final Set<Integer> visited = new HashSet<>();

    public void traverse(int idx) {
        if (visited.contains(idx))
            return;
        Stack<Integer> stack = new Stack<>();
        stack.push(idx);

        while(!stack.isEmpty()) {
            int node = stack.pop();
            visited.add(node);
            for(Integer adjacentNode: graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(adjacentNode)) {
                    stack.push(adjacentNode);
                }
            }
        }
    }

    public int connectedComponent(int n, int[][] edges) {
        for(int[] edge: edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int counter = 0;

        for(int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                traverse(i);
                counter++;
            }
        }

        return counter;
    }
}