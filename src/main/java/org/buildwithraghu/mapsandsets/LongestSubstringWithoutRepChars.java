package org.buildwithraghu.mapsandsets;

import java.util.HashMap;

public class LongestSubstringWithoutRepChars {

    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        int window = 0, maxLen = 0;
        HashMap<Character, Integer> lastOcc = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!lastOcc.containsKey(c)) {
                maxLen = Math.max(maxLen, i - window + 1);
            } else {
                int idx = lastOcc.get(c);
                for(int j = window; j <= idx; j++) {
                    lastOcc.remove(s.charAt(j));
                }
                window = idx + 1;
            }
            lastOcc.put(c, i);
        }
        return maxLen;
    }
}
