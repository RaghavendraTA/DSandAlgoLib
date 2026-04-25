package org.buildwithraghu.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BiPartite {

    public static boolean bfs(int[][] graph, int[] color, int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        color[start] = 1;
        while(!q.isEmpty()) {
            int u = q.poll();
            for (Integer v : graph[u]) {
                if (color[v] == color[u])
                    return false;
                if (color[v] == 0)
                    q.offer(v);
                color[v] = -color[u];
            }
        }
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length + 1];
        Arrays.fill(color, 0);
        for(int start = 0; start < graph.length; start++) {
            if (color[start] == 0 && !bfs(graph, color, start))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int V = 6;
        int[][] graph = new int[V][];
        graph[0] = new int[]{3, 7, 8};
        //System.out.println(result);
    }
}
