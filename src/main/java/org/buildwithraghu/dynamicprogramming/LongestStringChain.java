package org.buildwithraghu.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain {

    // https://leetcode.com/problems/longest-string-chain/
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        int longestWordSeq = 1;

        Arrays.sort(words, Comparator.comparingInt(String::length));

        for(String word: words) {
            int presentLength = 1;
            for(int i = 0; i < word.length(); i++) {
                String pred = word.substring(0, i) + word.substring(i+1);
                if (dp.containsKey(pred)) {
                    int prevL = dp.get(pred);
                    presentLength = Math.max(presentLength, prevL + 1);
                }
            }
            dp.put(word, presentLength);
            longestWordSeq = Math.max(longestWordSeq, presentLength);
        }
        return longestWordSeq;
    }
}
