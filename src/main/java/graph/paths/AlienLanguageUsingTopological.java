package graph.paths;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.*;

public class AlienLanguageUsingTopological {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void insertEdge(Integer u, Integer v) {

        graph.computeIfAbsent(u, k -> new ArrayList<>());
        graph.get(u).add(v);
    }

    public static void topologicalSortUtil(int vertex, Set<Integer> visited, Stack<Integer> stack) {

        visited.add(vertex);

        for (Integer i : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(i)) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public static void topologicalSort(int vertices) {

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited.contains(i)) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print((char)('a' + stack.pop()));
        }
    }

    static void printOrder(String[] words, int alpha)
    {
        for (int i = 0; i < words.length - 1; i++)
        {
            String word1 = words[i];
            String word2 = words[i+1];
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++)
            {
                if (word1.charAt(j) != word2.charAt(j))
                {
                    insertEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
                    break;
                }
            }
        }

        topologicalSort(alpha);
    }

    public static void main(String[] args) {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        printOrder(words, 4);

        System.out.println();

        graph = new HashMap<>();

        words = new String[]{"caa", "aaa", "aab"};
        printOrder(words, 3);
    }
}
