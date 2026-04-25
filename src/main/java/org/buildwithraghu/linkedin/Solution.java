package org.buildwithraghu.linkedin;


import java.util.*;

public class Solution {

    public String reverseWords(String s) {
        s = s.strip();
        String[] words = s.split(" ");
        return Arrays.stream(words)
                .filter(x -> x != null && !x.isEmpty())
                .toList()
                .reversed()
                .stream()
                .reduce((a, b) -> a + " " + b)
                .get();
    }

    static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reverseWords("the sky is blue"));
    }
}