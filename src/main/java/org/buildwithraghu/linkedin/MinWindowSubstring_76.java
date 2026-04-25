package org.buildwithraghu.linkedin;

import java.util.*;

public class MinWindowSubstring_76 {

    public String minWindow(String s, String target) {
        Map<Character, Integer> targetCounter = new HashMap<>();
        Map<Character, Integer> windowCounter = new HashMap<>();
        for(char c: target.toCharArray()) {
            targetCounter.merge(c, 1, Integer::sum);
        }
        int required = targetCounter.size();
        int formed = 0;

        int l = 0, r = 0;
        int[] ans = {-1, 0, 0};
        while(r < s.length()) {
            char c = s.charAt(r);
            windowCounter.merge(c, 1, Integer::sum);

            if (targetCounter.containsKey(c) &&
                windowCounter.get(c).intValue() == targetCounter.get(c).intValue()) {
                formed++;
            }

            while(formed == required && l <= r) {
                c = s.charAt(l);
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowCounter.put(c, windowCounter.get(c) - 1);
                if (targetCounter.containsKey(c) && windowCounter.get(c) < targetCounter.get(c)) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}
