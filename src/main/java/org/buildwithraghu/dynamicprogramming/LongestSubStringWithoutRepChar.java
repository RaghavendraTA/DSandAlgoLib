package org.buildwithraghu.dynamicprogramming;

import java.util.Arrays;

public class LongestSubStringWithoutRepChar {

    // https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);

        int maxLen = 0, start = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (lastIndex[c] >= start) {
                // Move start only if duplicate is inside current window
                start = lastIndex[c] + 1;
            }
            lastIndex[c] = i;
            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        LongestSubStringWithoutRepChar l = new LongestSubStringWithoutRepChar();
        System.out.println(l.lengthOfLongestSubstring("abba"));
    }
}
