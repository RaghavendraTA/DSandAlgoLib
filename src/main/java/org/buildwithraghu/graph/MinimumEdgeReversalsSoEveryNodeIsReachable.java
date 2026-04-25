package org.buildwithraghu.graph;

import java.util.ArrayList;
import java.util.List;

public class MinimumEdgeReversalsSoEveryNodeIsReachable {

    // https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable

    private List<int[]>[] graph;
    private int[] ans;

    public int[] minEdgeReversals(int n, int[][] edges) {
        graph = new List[n];
        ans = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(new int[]{v, 1});
            graph[v].add(new int[]{u, -1});
        }
        count0(0, -1);
        changeRoot(0, -1);
        return ans;
    }

    // undirected tree, no need visited array
    private void count0(int node, int parent) {
        for (int[] neighbor: graph[node]) {
            int v = neighbor[0];
            int dir = neighbor[1];
            if (v != parent) {
                if (dir == -1) {
                    ans[0]++;
                }
                count0(v, node);
            }
        }
    }

    private void changeRoot(int node, int parent) {
        for (int[] neighbor: graph[node]) {
            int v = neighbor[0];
            int dir = neighbor[1];
            if (v != parent) {
                ans[v] = ans[node] + dir;
                changeRoot(v, node);
            }
        }
    }
}
