package org.buildwithraghu.graph;

import java.util.HashMap;
import java.util.HashSet;

public class AlienDictionary {

    HashMap<Character, HashSet<Character>> graph = new HashMap<>();

    // https://leetcode.com/problems/alien-dictionary
    public String alienOrder(String[] words) {
        for(String word: words) {
            for(char c: word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        for(int i = 0; i < words.length-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            int minLen = Math.min(word1.length(), word2.length());

            if (word1.length() > word2.length() && word1.substring(0, minLen).equals(word2.substring(0, minLen))) {
                return "";
            }

            for(int j = 0; j < minLen; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.get(word1.charAt(j)).add(word2.charAt(j));
                    break;
                }
            }
        }

        HashMap<Character, Boolean> visited = new HashMap<>();
        StringBuilder order = new StringBuilder();
        for(char c: graph.keySet()) {
            if (!visited.containsKey(c)) {
                if (!dfs(c, visited, order))
                    return "";
            }
        }

        return order.reverse().toString();
    }

    boolean dfs(char c, HashMap<Character, Boolean> visited, StringBuilder order) {
        if (visited.containsKey(c))
            return visited.get(c);
        visited.put(c, false);
        for(char nei: graph.get(c)) {
            if (!dfs(nei, visited, order))
                return false;
        }
        visited.put(c, true);
        order.append(c);
        return true;
    }
}
