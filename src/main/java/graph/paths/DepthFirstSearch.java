package graph.paths;

/*
 * created by raghavendra.ta on 26-Jun-2021
 */

import java.util.*;

import static java.util.Arrays.asList;

public class DepthFirstSearch {

    public static void dfs(List<List<Integer>> edges) {

        Map<Integer, List<Integer>> graph = Graph.constructGraphMap(true, edges);
        Set<Integer> visited = new HashSet<>();

        for (Integer key : graph.keySet()) {
            if (visited.contains(key)) {
                continue;
            }

            Stack<Integer> stack = new Stack<>();
            stack.add(key);

            while (!stack.isEmpty()) {
                Integer node = stack.pop();
                visited.add(node);
                graph.getOrDefault(node, Collections.emptyList()).forEach(dest -> {
                    if (!visited.contains(dest)) {
                        System.out.println(node + " -> " + dest);
                        stack.add(dest);
                        visited.add(dest);
                    }
                });
            }
        }
    }

    public static void dfsUsingMatrix(List<List<Integer>> edges) {

        Set<Integer> nodes = new HashSet<>();
        edges.forEach(nodes::addAll);

        int n = nodes.size();
        int[][] arr = Graph.constructGraphMatrix(true, n, edges);

        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);

        for(Integer i: nodes.toArray(new Integer[n])) {
            if (visited[i])
                continue;

            Stack<Integer> stack = new Stack<>();
            stack.add(i);

            while(stack.size() > 0) {
                Integer node = stack.pop();
                visited[node] = true;
                for(int j = 1; j <= n; j++) {
                    if (arr[node][j] > 0 && !visited[j]) {
                        System.out.println(node + " -> " + j);
                        stack.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        List<List<Integer>> edges = asList(
                new ArrayList<>(asList(1, 2)),
                new ArrayList<>(asList(1, 3)),
                new ArrayList<>(asList(2, 3)),
                new ArrayList<>(asList(4, 5)),
                new ArrayList<>(asList(3, 4))
       );

        dfs(edges);
        System.out.println("------------------------------------------");
        dfsUsingMatrix(edges);
    }

}
