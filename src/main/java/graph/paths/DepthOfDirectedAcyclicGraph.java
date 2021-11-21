package graph.paths;

/*
 * created by raghavendra.ta on 14-Jul-2021
 */

import java.util.*;

public class DepthOfDirectedAcyclicGraph {

    static int[] inDegree;

    static int depth(int vertices, Map<Integer, List<Integer>> graph) {

        Queue<Integer> queue = new LinkedList<>();

        int counter = 0;

        for(int v = 0; v < vertices; v++) {
            if (inDegree[v] == 0) {
                queue.add(v);
            }
        }
        queue.add(-1);

        while(!queue.isEmpty()) {
            int v = queue.poll();
            if (v == -1) {
                counter++;
                if (!queue.isEmpty()) {
                    queue.add(-1);
                }
            }
            for(int w: graph.getOrDefault(v, Collections.emptyList())) {
                if (--inDegree[w] == 0) {
                    queue.add(w);
                }
            }
        }

        queue.clear();
        return counter;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Collections.singletonList(2));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Collections.singletonList(3));
        int noOfVertices = 4;
        inDegree = new int[noOfVertices];
        Arrays.fill(inDegree, 0);
        System.out.println(depth(noOfVertices, graph));
    }

}
