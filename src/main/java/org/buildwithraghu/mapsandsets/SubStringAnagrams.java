package org.buildwithraghu.mapsandsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubStringAnagrams {

    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    public List<Integer> findAnagrams_slow(String s, String p) {
        char[] pp = p.toCharArray();
        Arrays.sort(pp);
        p = new String(pp);
        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < s.length()-p.length(); i++) {
            pp = s.substring(i, i + p.length()).toCharArray();
            Arrays.sort(pp);
            if (p.equals(new String(pp))) {
                ans.add(i);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p.length() > s.length())
            return ans;

        int m = p.length(), n = s.length();
        int[] occ = new int[26], window = new int[26];

        for (char c : p.toCharArray())
            occ[c - 'a']++;

        for (int i = 0; i < m; ++i)
            window[s.charAt(i) - 'a']++;

        if (Arrays.equals(window, occ))
            ans.add(0);

        for (int i = m; i < n; ++i) {
            window[s.charAt(i - m) - 'a']--; // remove leftmost
            window[s.charAt(i) - 'a']++;     // add new rightmost
            if (Arrays.equals(window, occ))
                ans.add(i - m + 1);
        }

        return ans;
    }
}
