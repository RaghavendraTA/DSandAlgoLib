package org.buildwithraghu.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

public class WordBreak {

    private int isMatch(String s, int end, String word) {
        int start = end - word.length();
        if (start >= 0 && s.substring(start, end).equals(word)) {
            return start;
        }
        return -1;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        Arrays.fill(dp, false);
        dp[0] = true;
        for(int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int idx = isMatch(s, i, word);
                if (idx >= 0 && dp[idx]) {
                    dp[i] = true;
                    break; // no need to check more words
                }
            }
        }
        return dp[s.length()];
    }
}
