package org.buildwithraghu.google;

import java.util.*;

public class CourseSchedule_207 {

    Map<Integer, List<Integer>> graph;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new HashMap<>();
        for(int[] preq: prerequisites) {
            graph.computeIfAbsent(preq[0], k -> new ArrayList<>()).add(preq[1]);
        }
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && !dfs(i, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int u, int[] visited) {
        if (visited[u] == 1)
            return false;
        if (visited[u] == 2)
            return true;
        visited[u] = 1;
        for(int v: graph.getOrDefault(u, Collections.emptyList())) {
            if (!dfs(v, visited))
                return false;
        }
        visited[u] = 2;
        return true;
    }
}
