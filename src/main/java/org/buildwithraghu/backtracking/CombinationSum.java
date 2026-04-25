package org.buildwithraghu.Combinatorics;

import java.util.*;

public class CombinationSum {

    List<List<Integer>> ans = new ArrayList<>();

    // https://leetcode.com/problems/combination-sum/
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> stk = new ArrayList<>();
        Combination(candidates, stk, 0, target);
        return ans;
    }

    public void Combination(int[] candidates, List<Integer> stk, int i, int target) {
        if (target == 0)
            ans.add(new ArrayList<>(stk));
        else if (target < 0)
            return;
        for(int j = i; j < candidates.length; j++) {
            stk.add(candidates[j]);
            Combination(candidates, stk, j, target - candidates[j]);
            stk.removeLast();
        }
    }
}
