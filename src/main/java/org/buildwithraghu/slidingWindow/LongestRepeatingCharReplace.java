package org.buildwithraghu.slidingWindow;

public class LongestRepeatingCharReplace {

    public int characterReplacement(String s, int k) {
        int left = 0, max_repeat = 0, max_len = 0;
        int[] freq = new int[26];
        for(int right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'A';
            freq[c]++;
            max_repeat = Math.max(max_repeat, freq[c]);
            while ((right-left+1) - max_repeat > k) {
                freq[s.charAt(left)-'A']--;
                left++;
            }
            max_len = Math.max(max_len, right-left+1);
        }
        return max_len;
    }
}
