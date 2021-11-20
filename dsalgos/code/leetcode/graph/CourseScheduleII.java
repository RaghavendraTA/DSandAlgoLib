package leetcode.graph;

/*
 * created by raghavendra.ta on 16-Jul-2021
 */

import java.util.*;

public class CourseScheduleII {

    private final Map<Integer, List<Integer>> graph = new HashMap<>();

    private final Stack<Integer> stack = new Stack<>();

    private final Set<Integer> visited = new HashSet<>();

    // 1 -> 0
    // 0 -> 1 it's not possible to pick up any subject first, so it should return emptyList();

    public void topologicalSort(Set<Integer> visited, int idx) {
        visited.add(idx);
        for(Integer adjacentNode : graph.getOrDefault(idx, Collections.emptyList())) {
            if (!visited.contains(adjacentNode)) {
                topologicalSort(visited, adjacentNode);
            }
        }
        stack.push(idx);
    }

    public void topologicalSort(int vertices) {
        for(int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                topologicalSort(visited, i);
            }
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        for(int[] arr: prerequisites) {
            graph.putIfAbsent(arr[1], new ArrayList<>());
            graph.get(arr[1]).add(arr[0]);
        }

        topologicalSort(numCourses);

        int[] result = new int[stack.size()];
        int i = 0;
        while(!stack.isEmpty())
            result[i++] = stack.pop();
        return result;
    }

    public static void main(String[] args) {
        int[][] input = {{0,1}, {1,0}};
        int[] result = new CourseScheduleII().findOrder(2, input);
        System.out.println(Arrays.toString(result));
    }
}
