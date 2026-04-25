package org.buildwithraghu.mapsandsets;

import java.util.*;

public class LongestSubstringWithUniqueChars {

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        HashMap<Character, Integer> mp = new HashMap<>();
        int maxLen = 0, start = 0;

        for (int i = 0; i < s.length(); ++i) {
            if (mp.containsKey(s.charAt(i))) {
                start = Math.max(start, mp.get(s.charAt(i)) + 1);
            }
            mp.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen;
    }
}
