package org.buildwithraghu.google;

import java.util.*;

public class CourseScheduleII_210 {

    Map<Integer, List<Integer>> graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> ans = new ArrayList<>();
        int[] visited = new int[numCourses];
        graph = new HashMap<>();
        for(int[] preq: prerequisites) {
            graph.computeIfAbsent(preq[0], k -> new ArrayList<>()).add(preq[1]);
        }
        for(int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && !dfs(i, visited, ans)) {
                return new int[]{};
            }
        }
        return ans.stream().mapToInt(x -> x).toArray();
    }

    private boolean dfs(int u, int[] visited, List<Integer> ans) {
        if (visited[u] == 1)
            return false;
        if (visited[u] == 2)
            return true;
        visited[u] = 1;
        for(int v: graph.getOrDefault(u, Collections.emptyList())) {
            if (!dfs(v, visited, ans)) {
                return false;
            }
        }
        visited[u] = 2;
        ans.add(u);
        return true;
    }
}
