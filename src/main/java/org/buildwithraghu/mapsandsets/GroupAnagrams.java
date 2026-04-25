package org.buildwithraghu.mapsandsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

    // https://leetcode.com/problems/group-anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> ans = new HashMap<>();
        for(String s: strs) {
            char[] ss = s.toCharArray();
            Arrays.sort(ss);
            ans.computeIfAbsent(new String(ss), k -> new ArrayList<>()).add(s);
        }
        return ans.values().stream().toList();
    }

    public List<List<String>> groupAnagrams_slower(String[] strs) {
        HashMap<String, List<String>> hmap = new HashMap<>();
        for(String s: strs) {
            int[] freq = new int[26];
            for(char c: s.toCharArray()) {
                freq[c-'a']++;
            }
            StringBuilder key = new StringBuilder();
            for(int i: freq) {
                key.append(i+'a').append(i);
            }
            hmap.computeIfAbsent(key.toString(), (k2) -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(hmap.values());
    }
}
