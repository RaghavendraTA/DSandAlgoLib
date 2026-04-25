package org.buildwithraghu.mapsandsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Anagrams {

    // (easy) Valid Anagrams: https://leetcode.com/problems/valid-anagram/
    public boolean isAnagram(String s, String t) {
        int[] check = new int[26];
        for(char c: s.toCharArray())
            check[c-'a']++;
        for(char c: t.toCharArray())
            check[c-'a']--;
        return Arrays.stream(check).noneMatch(x -> x != 0);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hmap = new HashMap<>();
        for(String s: strs) {
            char[] tarr = s.toCharArray();
            Arrays.sort(tarr);
            String t = new String(tarr);
            hmap.putIfAbsent(t, new ArrayList<>());
            hmap.get(t).add(s);
        }
        return new ArrayList<>(hmap.values());
    }
}
