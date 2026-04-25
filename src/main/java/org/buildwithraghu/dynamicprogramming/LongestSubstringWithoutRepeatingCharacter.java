package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class LongestSubstringWithoutRepeatingCharacter {

    public int getLastOccurence(String s, int currentIdx, int startIdx, char c) {
        for(int i = startIdx; i <= currentIdx; i++) {
            if (c == s.charAt(i))
                return i;
        }
        return -1;
    }

    public int lengthOfLongestSubstring(String s) {
        int startIdx = 0, n = s.length(), maxLen = 0;
        for(int i = 1; i < n; i++) {
            int isFound = getLastOccurence(s, i - 1, startIdx, s.charAt(i));
            if (isFound != -1) {
                maxLen = Math.max(maxLen, i - startIdx);
                startIdx = isFound + 1;
            }
        }
        return Math.max(maxLen, n - startIdx);
    }
}
