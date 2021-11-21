package graph.paths;

/*
 * created by raghavendra.ta on 26-Jun-2021
 */

import trees.utils.IntPair;

import java.util.*;

import static java.util.Arrays.asList;

public class Dijkstra {

    public static Map<Integer, List<IntPair>> constructGraphWithCost(final List<List<Integer>> edges) {

        Map<Integer, List<IntPair>> map = new HashMap<>();

        edges.forEach(list -> {
            map.computeIfAbsent(list.get(0), k -> new ArrayList<>());
            map.computeIfAbsent(list.get(1), k -> new ArrayList<>());
            map.get(list.get(0)).add(new IntPair(list.get(1), list.get(2)));
            map.get(list.get(1)).add(new IntPair(list.get(0), list.get(2)));
        });

        return map;
    }

    public static void shortestPath(int start, List<List<Integer>> edges) {

        PriorityQueue<IntPair> queue = new PriorityQueue<>(Comparator.comparingInt(IntPair::getValue));
        Map<Integer, List<IntPair>> map = constructGraphWithCost(edges);
        Map<Integer, Integer> distance = new HashMap<>();

        queue.add(new IntPair(start, 0));

        while (queue.size() > 0) {
            IntPair node = queue.poll();
            if (distance.containsKey(node.getKey())) {
                continue;
            }
            distance.put(node.getKey(), node.getValue());
            for (IntPair dest : map.getOrDefault(node.getKey(), new ArrayList<>())) {
                if (distance.containsKey(dest.getKey())) {
                    continue;
                }
                queue.add(new IntPair(dest.getKey(), dest.getValue() + node.getValue()));
            }
        }

        System.out.println(distance);
    }

    public static void shortestPathUsingMatrix(int start, int n, List<List<Integer>> edges) {

        PriorityQueue<IntPair> queue = new PriorityQueue<>(Comparator.comparingInt(IntPair::getValue));
        Map<Integer, Integer> distance = new HashMap<>();

        int[][] arr = Graph.constructGraphMatrixWithCost(true, n, edges);

        queue.add(new IntPair(start, 0));

        while (queue.size() > 0) {
            IntPair node = queue.poll();
            if (distance.containsKey(node.getKey())) {
                continue;
            }
            distance.put(node.getKey(), node.getValue());
            for(int i = 1; i <= n; i++) {
                if (arr[node.getKey()][i] > 0 && !distance.containsKey(i)) {
                    queue.add(new IntPair(i, arr[node.getKey()][i] + node.getValue()));
                }
            }
        }

        System.out.println(distance);
    }

    public static void main(String[] args) {

        List<List<Integer>> edges = asList(
                asList(1, 2, 1),
                asList(1, 3, 2),
                asList(3, 4, 1),
                asList(2, 4, 10)
        );

        shortestPath(1, edges);
        shortestPathUsingMatrix(1, 4, edges);
    }
}
