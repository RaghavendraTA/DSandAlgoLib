package org.buildwithraghu.Combinatorics;

import java.util.*;

public class Combinations {

    public static void allCombinations(int[] a) {
        int n = a.length;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < (1<<n); i++) {
            for(int j = 0; j < n; j++) {
                if ((i&(1<<j)) > 0) {
                    ans.add(a[j]);
                }
            }
            System.out.println(ans);
            ans.clear();
        }
    }

    // Recursive Method
    public static void allCombinationsRec(int[] a, int k, Stack<Integer> stk) {
        System.out.println(stk);
        for(int i = k; i < a.length; i++) {
            stk.push(a[i]);
            allCombinationsRec(a, i+1, stk);
            stk.pop();
        }
    }

    public static void main(String[] args) {
        // allCombinationsRec(new int[]{1, 2, 3, 4}, 0, new Stack<>());
        allCombinations(new int[]{1, 2, 3, 4});
    }
}
