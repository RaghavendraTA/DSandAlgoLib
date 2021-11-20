package string.slidingwindow;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {

    /** My solution */
    public static boolean hasAllChars(String s, int startIdx, int endIdx, Map<Character, Integer> tMap) {
        int j = 0;
        for(int i = startIdx; i <= endIdx; i++) {
            tMap.computeIfPresent(s.charAt(i), (k, v) -> v - 1);
        }
        return tMap.values().stream().noneMatch(x -> x > 0);
    }

    public static Map<Character, Integer> constructTarget(String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for(char c: t.toCharArray()) {
            tMap.putIfAbsent(c, 0);
            tMap.computeIfPresent(c, (k, v) -> v + 1);
        }
        return tMap;
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = constructTarget(t);
        int i = 0, j = t.length() - 1;
        String ans = null;
        while(i <= j && j < s.length()) {
            if (hasAllChars(s, i, j, new HashMap<>(tMap))) {
                if (ans == null || j - i < ans.length())
                    ans = s.substring(i, j + 1);
                i++;
            } else {
                j++;
            }
        }
        return ans == null ? "" : ans;
    }
}
