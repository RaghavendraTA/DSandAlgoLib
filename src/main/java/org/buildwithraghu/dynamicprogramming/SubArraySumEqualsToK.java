package org.buildwithraghu.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsToK {

    // https://leetcode.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0] == k ? 1 : 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.merge(sum, 1, Integer::sum);
        }
        return ans;
    }
}
