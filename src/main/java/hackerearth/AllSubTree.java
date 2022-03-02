package hackerearth;

import java.util.*;

public class AllSubTree {

    public static Map<Integer, Set<Integer>> childNodes = new HashMap<>();

    public static Set<Integer> visited = new HashSet<>();

    public static int productOfSubsetSums(Set<Integer> arr) {
        int ans = 1;
        for (Integer i : arr)
            ans = ans * (i + 1);
        return ans - 1;
    }

    public static void getAllSubTrees(int node, Map<Integer, List<Integer>> graph) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        List<Integer> nodes = graph.getOrDefault(node, Collections.emptyList());
        childNodes.putIfAbsent(node, new HashSet<>());
        for (Integer n : nodes) {
            if (!visited.contains(n))
                childNodes.get(node).add(n);
            getAllSubTrees(n, graph);
        }
    }

    public static void addLine(Map<Integer, List<Integer>> graph, int u, int v) {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        visited.clear();
        childNodes.clear();
        addLine(graph, 1, 2);
        addLine(graph, 1, 3);
        addLine(graph, 1, 4);
        addLine(graph, 1, 6);
        addLine(graph, 1, 7);
        addLine(graph, 2, 5);

        getAllSubTrees(1, graph);
        System.out.println(childNodes);
    }
}
