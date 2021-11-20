package graph.paths;

/*
 * created by raghavendra.ta on 17-Jul-2021
 */

import java.util.*;

public class CutVertexOrArticulationPoints {

    /**
     *  Non-optimal solution => getCutVertexCount()
     *  complexity = O(V(V+E))
     *
     *  1. count connected component keep it in a variable
     *  2. no skip each node then take count of connected component
     *      => if the newly found count > count found in step1
     *      => then cutVertexCount++;
     */
    public void traverse(Map<Integer, List<Integer>> graph, Set<Integer> visited, int idx) {
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

    public int getConnectedComponent(int n, Map<Integer, List<Integer>> graph) {
        int counter = 0;
        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                traverse(graph, visited, i);
                counter++;
            }
        }
        return counter;
    }

    public Map<Integer, List<Integer>> getGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge: edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    public List<Integer> getCutVertexCount(int n, int[][] edges) {

        int connectedComponents = getConnectedComponent(n, getGraph(n, edges));
        List<Integer> cutVertexCount = new ArrayList<>();

        for(int node = 0; node < n; node++) {
            Map<Integer, List<Integer>> temp = getGraph(n, edges);
            temp.remove(node);
            final Integer listNode = node;
            temp.forEach((k, v) -> v.remove(listNode));
            if (getConnectedComponent(n, temp) > connectedComponents) {
                cutVertexCount.add(node);
            }
        }
        return cutVertexCount;
    }

    /**
     * Optimal Solution:
     *
     * Case 1: If the node U is root of dfs tree and has at least 2 children (Subgraph)
     *         then U is an articulation Point
     *
     * Case 2: If the non-root node V has a subgraph does not connect to root node U
     *         then V is an articulation Point
     *
     * Reference video: https://www.youtube.com/watch?v=64KK9K4RpKE
     */
    public void findCutVertexCount(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = getGraph(n, edges);
        int[] visited = new int[n], low = new int[n], parent = new int[n];
        boolean[] articulationPoint = new boolean[n];

        Arrays.fill(visited, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);
        Arrays.fill(articulationPoint, false);

        for(int i = 0; i < n; i++) {
            if (visited[i] == -1) {
                dfs(i, visited, low, parent, articulationPoint, graph);
            }
        }

        for(int i = 0; i < n; i++) {
            if (articulationPoint[i]) {
                System.out.print(i + ", ");
            }
        }
    }

    private static int steps = 0;

    public void dfs(int u, int[] visited, int[] low, int[] parent, boolean[] articulationPoint,
                    Map<Integer, List<Integer>> graph) {

        visited[u] = steps;
        low[u] = steps;
        steps++;
        int children = 0;

        for(int v: graph.getOrDefault(u, Collections.emptyList())) {
            if (visited[v] == -1) {
                children++;
                parent[v] = u;
                dfs(v, visited, low, parent, articulationPoint, graph);

                low[u] = Math.min(low[u], low[v]);

                if (parent[u] == -1 && children > 1)                // Case-1
                    articulationPoint[u] = true;

                if (parent[u] != -1 && low[v] >= visited[u]) {      // Case-2
                    articulationPoint[u] = true;
                }
            }
            else if (v != parent[u]) {
                low[u] = Math.min(low[u], visited[v]);
            }
        }
    }

    public static void main(String[] args) {
        CutVertexOrArticulationPoints C = new CutVertexOrArticulationPoints();
        int[][] edges = {{0,1},{0,2},{1,2},{0,3},{3, 4},{3, 5}};
        System.out.println(C.getCutVertexCount(6, edges));

        C.findCutVertexCount(6, edges);
    }

}
