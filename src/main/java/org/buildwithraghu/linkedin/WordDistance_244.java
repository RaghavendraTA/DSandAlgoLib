package org.buildwithraghu.linkedin;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class WordDistance_244 {

    class WordDistance {

        private Map<String, TreeSet<Integer>> dict;

        public WordDistance(String[] wordsDict) {
            dict = new HashMap<>();

            for (int i = 0; i < wordsDict.length; i++) {
                dict.computeIfAbsent(wordsDict[i], k -> new TreeSet<>()).add(i);
            }
        }

        public int shortest(String word1, String word2) {
            int ans = Integer.MAX_VALUE;

            TreeSet<Integer> positions1 = dict.get(word1);
            TreeSet<Integer> positions2 = dict.get(word2);

            for (int pos : positions1) {

                // equivalent to lower_bound(pos)
                Integer ceil = positions2.ceiling(pos);
                if (ceil != null) {
                    ans = Math.min(ans, Math.abs(pos - ceil));
                }

                // equivalent to element before lower_bound
                Integer floor = positions2.floor(pos);
                if (floor != null) {
                    ans = Math.min(ans, Math.abs(pos - floor));
                }
            }

            return ans;
        }
    }
}
