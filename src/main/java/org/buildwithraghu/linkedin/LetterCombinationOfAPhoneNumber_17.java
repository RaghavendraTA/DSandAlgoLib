package org.buildwithraghu.linkedin;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfAPhoneNumber_17 {

    public static String[] symbols = new String[] {
            " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return ans;
        }
        List<String> temp = new ArrayList<>();
        int d = digits.charAt(0) - '0';
        for(char s: symbols[d].toCharArray()) {
            ans.add(String.valueOf(s));
        }
        for(int i = 1; i < digits.length(); i++) {
            d = digits.charAt(i) - '0';
            for(char s: symbols[d].toCharArray()) {
                ans.forEach(x -> temp.add(x + s));
            }
            ans = temp.stream().toList();
            temp.clear();
        }
        //System.out.println(ans);
        return ans;
    }
}
