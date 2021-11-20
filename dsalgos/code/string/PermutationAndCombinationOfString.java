package string;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

import java.util.Arrays;

public class PermutationAndCombinationOfString {

    static void permute(int depth, StringBuilder permutation, boolean[] used, String original) {
        int len = original.length();
        if (depth == len)
            System.out.println(permutation);
        else {
            for(int i = 0; i < len; i++) {
                if (!used[i]) {
                    used[i] = true;
                    permutation.setCharAt(depth, original.charAt(i));
                    permute(depth + 1, permutation, used, original);
                    used[i] = false;
                }
            }
        }
    }

    static void combination(int depth, StringBuilder combination, int start, String original) {
        int len = original.length();
        for(int i = start; i < len; i++) {
            combination.setCharAt(depth, original.charAt(i));
            System.out.println(combination.substring(0, depth + 1));
            if (i < len - 1)
                combination(depth + 1, combination, start + 1, original);
        }
    }

    public static void main(String[] args) {
        String name = "rag";
        boolean[] used = new boolean[name.length()];
        Arrays.fill(used, false);
        //permute(0, new StringBuilder(name), used, name);

        combination(0, new StringBuilder(name), 0, name);
    }

}
