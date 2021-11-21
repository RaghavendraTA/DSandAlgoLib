package graph.paths;

/*
 * created by raghavendra.ta on 26-Jun-2021
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    public static Map<Integer, List<Integer>> constructGraphMap(final boolean biDirectional,
                                                                final List<List<Integer>> edges) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        edges.forEach(edge -> {
            map.computeIfAbsent(edge.get(0), k -> new ArrayList<>());
            map.get(edge.get(0)).add(edge.get(1));
            if (biDirectional) {
                map.computeIfAbsent(edge.get(1), k -> new ArrayList<>());
                map.get(edge.get(1)).add(edge.get(0));
            }
        });
        return map;
    }

    public static int[][] constructGraphMatrixWithCost(final boolean biDirectional,
                                                       final int n,
                                                       final List<List<Integer>> edges) {

        int[][] mat = new int[n + 1][n + 1];
        for (List<Integer> edge : edges) {
            mat[edge.get(0)][edge.get(1)] = edge.get(2);
            if (biDirectional) {
                mat[edge.get(1)][edge.get(0)] = edge.get(2);
            }
        }
        return mat;
    }

    public static int[][] constructGraphMatrix(final boolean biDirectional,
                                               final int n,
                                               final List<List<Integer>> edges) {

        edges.forEach(list -> list.add(1));
        return constructGraphMatrixWithCost(biDirectional, n, edges);
    }


}
