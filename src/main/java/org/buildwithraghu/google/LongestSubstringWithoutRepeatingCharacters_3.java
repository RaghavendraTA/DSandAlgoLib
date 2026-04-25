package org.buildwithraghu.google;

// 3. Longest Substring Without Repeating Characters

import java.util.Arrays;

class LongestSubstringWithoutRepeatingCharacters_3 {
	
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), maxLen = 0, start = 0;
		int[] locc = new int[128];
		Arrays.fill(locc, -1);
		for(int i = 0; i < n; i++) {
			int c = s.charAt(i);
			if (locc[c] >= 0) {
				start = Math.max(start, locc[c] + 1);
			}
			locc[c] = i;
			maxLen = Math.max(maxLen, i-start+1);
		}
		return maxLen;
    }
	
	public static void main(String[] args) {
		LongestSubstringWithoutRepeatingCharacters_3 s = new LongestSubstringWithoutRepeatingCharacters_3();
		System.out.println(s.lengthOfLongestSubstring("abba"));
	}
}