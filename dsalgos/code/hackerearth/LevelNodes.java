package hackerearth;

/*
 * created by raghavendra.ta on 22-Aug-2021
 */

import java.util.*;

public class LevelNodes {

    public static long numberOfNodesInLevelX(Map<Integer, List<Integer>> graph,
                                            int n,
                                            int x) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(1);
        queue.add(1);
        int[] level = new int[n+1];
        level[1] = 1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i : graph.getOrDefault(node, Collections.emptyList())) {
                if (!visited.contains(i)) {
                    level[i] = level[node] + 1;
                    queue.add(i);
                    visited.add(i);
                }
            }
        }
        return Arrays.stream(level).filter(i -> i == x).count();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 1; i < n; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            graph.putIfAbsent(s, new ArrayList<>());
            graph.putIfAbsent(d, new ArrayList<>());
            graph.get(s).add(d);
            graph.get(d).add(s);
        }
        int x = sc.nextInt();
        System.out.println(numberOfNodesInLevelX(graph, n, x));
    }

}
