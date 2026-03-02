package SetsAndHashes;

import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsOfaPhoneNo_17 {

    public static String[] symbols = new String[] {
        " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        LinkedList<String> left = new LinkedList<>();
        for(char c: digits.toCharArray()) {
            int idx = c - '0';
            if (left.isEmpty()) {
                for (char cc : symbols[idx].toCharArray()) {
                    left.add(cc + "");
                }
                continue;
            }
            LinkedList<String> right = new LinkedList<>();
            while(!left.isEmpty()) {
                for(char cc: symbols[idx].toCharArray()) {
                    right.add(left.getFirst() + cc);
                }
                left.removeFirst();
            }
            left = right;
        }
        return left;
    }
}
