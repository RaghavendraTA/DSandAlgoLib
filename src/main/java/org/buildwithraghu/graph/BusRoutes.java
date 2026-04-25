package org.buildwithraghu.graph;

import java.util.*;

public class BusRoutes {

    // https://leetcode.com/problems/bus-routes/
    public int numBusesToDestination_o(int[][] routes, int source, int target) {
        if (source == target)
            return 0;

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for(int r = 0; r < routes.length; r++) {
            for(int stop: routes[r]) {
                graph.computeIfAbsent(stop, x -> new ArrayList<>()).add(r);
            }
        }

        if (graph.get(source) == null || graph.get(target) == null)
            return -1;

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[routes.length];
        Arrays.fill(vis, false);

        for(int route: graph.get(source)) {
            q.add(route);
            vis[route] = true;
        }

        int count = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int route = q.remove();

                for(int stop: routes[route]) {
                    if (stop == target) {
                        return count;
                    }

                    for(int nextRoute: graph.get(stop)) {
                        if (!vis[nextRoute]) {
                            vis[nextRoute] = true;
                            q.add(nextRoute);
                        }
                    }
                }
            }
            count++;
        }

        return -1;
    }
}
