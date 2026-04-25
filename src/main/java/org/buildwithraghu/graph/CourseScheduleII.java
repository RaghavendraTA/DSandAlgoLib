package org.buildwithraghu.graph;

import java.util.*;

public class CourseScheduleII {

    // https://leetcode.com/problems/course-schedule-ii
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses + 1];
        for(int i = 0; i <= numCourses; i++)
            graph[i] = new ArrayList<>();
        for(int[] p: prerequisites) {
            graph[p[0]].add(p[1]);
        }
        Stack<Integer> stk = new Stack<>();
        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if (visited[i] == 0)
                if (!deepDfs(graph, i, stk, visited))
                    return new int[]{};
        }
        return stk.stream().mapToInt(x -> x).toArray();
    }

    boolean deepDfs(List<Integer>[] graph, int u, Stack<Integer> stk, int[] visited) {
        if (visited[u] == 1) return false;
        if (visited[u] == 2) return true;
        visited[u] = 1;
        for(int v: graph[u]) {
            if (!deepDfs(graph, v, stk, visited)) {
                return false;
            }
        }
        visited[u] = 2;
        stk.push(u);
        return true;
    }
}
