package org.buildwithraghu.google;

import java.util.*;

public class ParallelCourses {

    Map<Integer, List<Integer>> graph;

    public int minimumSemesters(int n, int[][] relations) {
        graph = new HashMap<>();
        int[] inDegree = new int[n+1];
        for(int[] preq: relations) {
            graph.computeIfAbsent(preq[0], k -> new ArrayList<>()).add(preq[1]);
            inDegree[preq[1]]++;
        }

        LinkedList<Integer> bfsQ = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                bfsQ.add(i);
            }
        }

        int step = 0, studied = 0;
        while(!bfsQ.isEmpty()) {
            step++;
            LinkedList<Integer> nextQ = new LinkedList<>();
            for(int i: bfsQ) {
                studied++;
                for(int node: graph.getOrDefault(i, Collections.emptyList())) {
                    inDegree[node]--;
                    if (inDegree[node] == 0)
                        nextQ.add(node);
                }
            }
            bfsQ = nextQ;
        }

        return studied == n ? step : -1;
    }

}
