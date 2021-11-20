package hackerrank;

/*
 * created by raghavendra.ta on 20-Oct-2021
 */

import java.util.*;

public class FindNearestClone {

    static class Pair {

        public Integer u;
        public Long k;

        Pair(final Integer u, final Long k) {
            this.u = u;
            this.k = k;
        }
    }

    public static void bfs(Integer n,
                           int[] coloring,
                           Integer color,
                           List<List<Integer>> graph) {

        long[] dist = new long[n+1];
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingLong(p -> p.k));
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);

        int start = 1;
        dist[start] = 0L;
        queue.add(new Pair(start, 0L));
        visited[start] = true;

        while (!queue.isEmpty()) {

            Pair p = queue.poll();
            for (Integer v : graph.get(p.u)) {
                if (visited[v]) {
                    continue;
                }
                queue.add(new Pair(v, p.k + 1));
                dist[v] = p.k + 1;
                visited[v] = true;
            }
        }

        PriorityQueue<Long> q = new PriorityQueue<>();
        for(int i = 1; i <= n; i++) {
            if (coloring[i] == color) {
                q.add(dist[i]);
            }
        }

        if (coloring[start] == color) {
            q.poll();
            if (q.size() > 0)
                System.out.println(q.poll());
        }
        else if (q.size() > 1)
                System.out.println(q.poll() + q.poll());
        else
            System.out.println("-1");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int u, v;

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            u = sc.nextInt();
            v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] coloring = new int[n+1];
        for (int i = 1; i <= n; i++) {
            coloring[i] = sc.nextInt();
        }
        int color = sc.nextInt();
        bfs(n, coloring, color, graph);
    }
}
