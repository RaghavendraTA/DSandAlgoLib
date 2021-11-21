package hackerrank;

/*
 * created by raghavendra.ta on 09-Nov-2021
 */

import java.util.*;

public class JourneyToTheMoon {

    public static int[] connectedComponent(int n, int[][] graph) {

        int[] result = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < n; i++) {

            if (visited.contains(i)) {
                continue;
            }

            queue.add(i);
            visited.add(i);
            result[i] = 1;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (graph[u][v] > 0 && !visited.contains(v)) {
                        queue.add(v);
                        visited.add(v);
                        result[i]++;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int[][] graph = new int[n][n];

        for(int i = 0; i < p; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        int[] component = connectedComponent(n, graph);

        long totalWays = (long) n *(n-1) / 2;
        long sum = 0;
        for (final int j : component) {
            sum += (long) j * (j - 1) / 2;
        }

        System.out.println(totalWays - sum);
    }
}
