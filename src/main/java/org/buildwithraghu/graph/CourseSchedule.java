package org.buildwithraghu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseSchedule {

    // https://leetcode.com/problems/course-schedule
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e: prerequisites) {
            graph[e[0]].add(e[1]);
        }
        boolean[] cycle = new boolean[numCourses];
        Arrays.fill(cycle, false);
        for(int i = 0; i < numCourses; i++) {
            if (!dfs(i, cycle, graph))
                return false;
        }
        return true;
    }

    private boolean dfs(int u, boolean[] cycle, List<Integer>[] graph) {
        if (cycle[u])
            return false;
        if (graph[u].isEmpty())
            return true;
        cycle[u] = true;
        for(int v: graph[u]) {
            if (!dfs(v, cycle, graph))
                return false;
        }
        cycle[u] = false;
        graph[u].clear();
        return true;
    }
}