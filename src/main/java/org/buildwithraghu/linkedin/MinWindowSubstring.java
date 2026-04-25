package org.buildwithraghu.linkedin;

import java.util.Arrays;

public class MinWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        String ans = new MinWindowSubstring().minWindow(s, t);
        System.out.println(ans);
    }

    public String minWindow(String s, String target) {
        if (s.isEmpty() || target.isEmpty() || target.length() > s.length()) {
            return "";
        }

        int[] targetMap = new int[128];
        for (int i = 0; i < target.length(); i++)
            targetMap[target.charAt(i)]++;

        int[] windowCounter = new int[128];
        int required = Arrays.stream(targetMap).map(x -> x > 0 ? 1 : 0).sum();
        int l = 0, r = 0, formed = 0;
        int[] ans = {-1, 0, 0};

        while (r < s.length()) {
            char c = s.charAt(r);
            windowCounter[c]++;
            if (targetMap[c] > 0 && windowCounter[c] == targetMap[c]) {
                formed++;
            }
            while (l <= r && formed == required) {
                c = s.charAt(l);
                if (ans[0] == -1 || r - l + 1 < ans[0]) {
                    ans[0] = r - l + 1;
                    ans[1] = l;
                    ans[2] = r;
                }
                windowCounter[c]--;
                if (targetMap[c] > 0 && windowCounter[c] < targetMap[c]) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

}