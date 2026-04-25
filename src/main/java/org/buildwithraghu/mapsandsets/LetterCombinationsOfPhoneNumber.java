package org.buildwithraghu.mapsandsets;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfPhoneNumber {
    public static String[] symbols = new String[] {
        " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations_iteration(String digits) {
        List<String> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for(char d: digits.toCharArray()) {
            int digit = d-'0';
            if (ans.isEmpty()) {
                ans.addAll(symbols[digit].chars().mapToObj(c -> String.valueOf((char)c)).toList());
            } else {
                for(String t: ans) {
                    for (char s : symbols[digit].toCharArray()) {
                        temp.add(t + s);
                    }
                }
                ans.clear();
            }
            ans.addAll(temp);
            temp.clear();
        }
        return ans;
    }

    // using dfs
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        dfs(digits, 0, ans, new StringBuilder());
        return ans;
    }

    private void dfs(String digits, int idx, List<String> ans, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        int num = digits.charAt(idx) - '0';
        for (char c : symbols[num].toCharArray()) {
            sb.append(c);
            // set.add(c);
            dfs(digits, idx + 1, ans, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber lcpn = new LetterCombinationsOfPhoneNumber();
        System.out.println(lcpn.letterCombinations("456"));
    }
}
