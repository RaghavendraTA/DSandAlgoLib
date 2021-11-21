package hackerrank;

import java.io.IOException;
import java.util.*;

/*
 * created by raghavendra.ta on 14-Oct-2021
 */

public class MinCostForLibraryAccessForHackerLand {

    public static List<List<Integer>> graph = new ArrayList<>(100005);
    public static boolean[] visited = new boolean[100005];
    public static long nodes;

    static {
        Arrays.fill(visited, false);
        for(int i = 0; i <= 100005; i++)
            graph.add(new ArrayList<>());
    }

    static void dfs(int n) {
        nodes++;
        visited[n] = true;
        for(int i: graph.get(n)) {
            if (!visited[i])
                dfs(i);
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            long c_lib = sc.nextLong();
            long c_road = sc.nextLong();
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt(), v = sc.nextInt();
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            long cost = 0;

            for(int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    nodes = 0;
                    dfs(i);
                    cost += c_lib;
                    if (c_lib > c_road)
                        cost += c_road * (nodes - 1);
                    else
                        cost += c_lib * (nodes - 1);
                }
            }

            System.out.println(cost);

            for(int i = 0; i <= n; i++) {
                graph.get(i).clear();
                visited[i] = false;
            }
        }
    }
}