package org.buildwithraghu.mapsandsets;

public class LongestSubstringWithoutRepChar {

    // https://leetcode.com/problems/longest-substring-without-repeating-characters
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        int window = 0, maxLen = 0;
        int[] lastOcc = new int[128];
        for(int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (lastOcc[c] == 0) {
                maxLen = Math.max(maxLen, i - window + 1);
            } else {
                int idx = lastOcc[c] - 1;
                for(int j = window; j <= idx; j++) {
                    lastOcc[s.charAt(j)] = 0;
                }
                window = idx + 1;
            }
            lastOcc[c] = i + 1;
        }
        return maxLen;
    }
}