package org.buildwithraghu.linkedin;

import java.util.*;

public class WordLadder_127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return level;
                }

                char[] arr = word.toCharArray();
                for(int j = 0; j < arr.length; j++) {
                    char original = arr[j];
                    for(char c = 'a'; c <= 'z'; c++) {
                        arr[j] = c;
                        String next = new String(arr);
                        if (dict.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.add(next);
                        }
                    }
                    arr[j] = original;
                }
            }
            level++;
        }
        return 0;
    }

    static void main() {
        WordLadder_127 wl = new WordLadder_127();
        int ans = wl.ladderLength("hot", "cog", Arrays.asList("hot","dog"));
        System.out.println(ans);
    }
}
