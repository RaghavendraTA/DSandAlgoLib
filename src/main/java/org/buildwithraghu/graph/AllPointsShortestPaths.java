package graph.paths;

/*
 * created by raghavendra.ta on 20-Aug-2021
 */

import utilities.ArrayUtils;

import java.util.Arrays;
import java.util.Scanner;

public class AllPointsShortestPaths {

    public static Integer[][] floydWarshallAlgorithm(int V, Integer[][] graph) {

        // Path between 2 nodes should be represented as INF if there is no path exist

        Integer[][] dist = new Integer[V + 1][V + 1];

        for(int i = 0; i <= V; i++)
            System.arraycopy(graph[i], 0, dist[i], 0, V+1);

        for (int k = 0; k <= V; k++) {
            for (int i = 0; i <= V; i++) {
                for (int j = 0; j <= V; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Integer[][] graph = new Integer[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], 9999);
            graph[i][i] = 0;
        }

        for(int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph[u][v] = w;
        }

        Integer[][] dist = floydWarshallAlgorithm(n, graph);

        int q = sc.nextInt();

        for(int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            System.out.println(dist[u][v] == 9999 ? -1 : dist[u][v]);
        }

    }
}
