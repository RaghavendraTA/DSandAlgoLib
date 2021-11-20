package airthmetic;

/*
 * created by raghavendra.ta on 12-Jul-2021
 */

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combo = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target, List<Integer> v, int i) {
        if (target <= 0) {
            if (target == 0) combo.add(v);
            return combo;
        }
        for(int j = i; j < candidates.length; j++) {
            v.add(candidates[j]);
            combinationSum(candidates, target - candidates[j], new ArrayList<>(v), j);
            v.remove(v.size() - 1);
        }
        return combo;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, target, new ArrayList<>(), 0);
    }
}
