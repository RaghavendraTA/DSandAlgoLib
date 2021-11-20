package graph.paths;

/*
 * created by raghavendra.ta on 28-Jun-2021
 */

import trees.utils.IntPair;

import java.util.*;

import static java.util.Arrays.asList;

public class BreadthFirstSearch {

    public static void bfs(List<List<Integer>> edges) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        edges.forEach(edge -> {
            map.computeIfAbsent(edge.get(0), k -> new ArrayList<>());
            map.get(edge.get(0)).add(edge.get(1));
        });

        Deque<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for(Integer start: map.keySet()) {

            if (visited.contains(start))
                continue;

            queue.addLast(start);

            while (queue.size() > 0) {
                Integer node = queue.pollFirst();
                if (visited.contains(node))
                    continue;
                visited.add(start);
                for (Integer dest : map.getOrDefault(node, Collections.emptyList())) {
                    if (!visited.contains(dest)) {
                        System.out.println(node + " -> " + dest);
                        queue.addLast(dest);
                        visited.add(node);
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

        bfs(edges);
    }
}
