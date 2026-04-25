package org.buildwithraghu.slidingWindow;

import java.util.HashMap;

public class LongestSubStrWithoutRepeat {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int lastOcc = 0, maxLen = 0;
        for(int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                lastOcc = Math.max(lastOcc, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLen = Math.max(maxLen, i-lastOcc+1);
        }
        return maxLen;
    }
}
