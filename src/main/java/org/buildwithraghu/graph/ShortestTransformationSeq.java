package org.buildwithraghu.graph;

import java.util.*;

public class ShortestTransformationSeq {

    public int shortestTransformationSeq(String start, String end, String[] dict) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, dict);

        if (!set.contains(start) || !set.contains(end))
            return 0;
        if (start.equals(end))
            return 1;

        char[] lowerAlpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int dist = 0;
        Queue<String> que = new ArrayDeque<>();
        que.offer(start);
        Set<String> visited = new HashSet<>();
        visited.add(start);

        while(!que.isEmpty()) {
            for(int i = 0; i < que.size(); i++) {
                String currWord = que.poll();
                if (currWord.equals(end))
                    return dist + 1;

                for(int j = 0; j < currWord.length(); j++) {
                    for(char c: lowerAlpha) {
                        String nextWord = currWord.substring(0, i) + c + currWord.substring(i+1);
                        if (set.contains(nextWord) && !visited.contains(nextWord)) {
                            visited.add(nextWord);
                            que.add(nextWord);
                        }
                    }
                }
            }
            dist++;
        }
        return 0;
    }
}
