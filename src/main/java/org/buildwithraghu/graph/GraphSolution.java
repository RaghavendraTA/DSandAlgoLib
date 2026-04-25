package microsoft;

import java.util.*;

public class Solution {

    boolean[] visited;
    int ans = 0;
    Map<Integer, Integer> childrenCount;

    public int dfs(int u, int s, Map<Integer, List<Integer>> graph) {
        if (visited[u]) {
            return s;
        }
        visited[u] = true;
        int sum = 1;
        for(int v: graph.getOrDefault(u, Collections.emptyList())) {
            int total = dfs(v, s, graph);
            childrenCount.put(v, total);
            sum += total;
            visited[v] = true;
        }
        return sum;
    }

    public void removeFromGraph(Map<Integer, List<Integer>> graph, Integer obj) {
        Set<Integer> keys = new HashSet<>(graph.keySet());
        for(int k: keys) {
            List<Integer> ls = graph.get(k);
            ls.remove(obj);
        }
    }

    public void calculatePools(int u, Map<Integer, List<Integer>> graph) {
        if (visited[u]) {
            return;
        }
        visited[u] = true;
        for(int v: graph.getOrDefault(u, Collections.emptyList())) {
            if (!visited[v]) {
                calculatePools(v, graph);
                int temp = (childrenCount.get(v) / 5) + (childrenCount.get(v) % 5 > 0 ? 1 : 0);
                ans += temp;
            }
        }
    }

    public int solution(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0 || A.length != B.length) {
            return 0;
        } else if (A.length == 1) {
            return 1;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        visited = new boolean[A.length + 1];
        ans = 0;
        childrenCount = new HashMap<>();
        for(int i = 0; i < A.length; i++) {
            graph.putIfAbsent(A[i], new ArrayList<>());
            graph.putIfAbsent(B[i], new ArrayList<>());
            graph.get(A[i]).add(B[i]);
            graph.get(B[i]).add(A[i]);
        }
        Set<Integer> keys = new HashSet<>(graph.keySet());
        for(int k: keys) {
            if (k == 0) continue;
            List<Integer> ls = graph.get(k);
            if (ls.size() == 1 && ls.get(0) == 0) {
                ans++;
                graph.remove(k);
                graph.getOrDefault(0, Collections.emptyList()).remove(Integer.valueOf(k));
            } else if (ls.size() == 1) {
                graph.remove(k);
            }
        }
        int totalSum = dfs(0, 0, graph);
        childrenCount.put(0, totalSum);
        keys = new HashSet<>(childrenCount.keySet());

        for(int k: keys) {
            if (childrenCount.get(k) == 1) {
                ans++;
                childrenCount.remove(Integer.valueOf(k));
                removeFromGraph(graph, Integer.valueOf(k));
            }
        }
        Arrays.fill(visited, false);
        calculatePools(0, graph);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(
                new int[]{0, 1, 2, 3, 4, 5, 6},
                new int[]{1, 2, 3, 4, 5, 6, 7}
        ));
    }
}
