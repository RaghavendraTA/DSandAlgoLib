package org.buildwithraghu.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Sum234 {

    // https://leetcode.com/problems/two-sum
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // https://leetcode.com/problems/3sum/description/
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int k = 0; k < nums.length-2; k++) {
            // Skip duplicates for k
            if (k > 0 && nums[k] == nums[k - 1]) continue;

            int target = -nums[k];
            int i = k+1, j = nums.length-1, sum;
            while(i < j) {
                sum = nums[i] + nums[j];
                if (sum == target) {
                    ans.add(List.of(nums[k], nums[i], nums[j]));
                    // Skip duplicates for i and j
                    while (i < j && nums[i] == nums[i + 1]) i++;
                    while (i < j && nums[j] == nums[j - 1]) j--;
                    i++;
                    j--;
                }
                else if (sum < target) i++;
                else j--;
            }
        }
        return ans;
    }

    public void fourSum(int[] nums) {

    }
}
