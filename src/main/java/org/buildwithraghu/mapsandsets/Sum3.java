package org.buildwithraghu.mapsandsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {

    // https://leetcode.com/problems/3sum/
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int target = -nums[i];
            int l = i + 1, r = n-1;
            while(l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while(l < r && nums[l-1] == nums[l]) l++;
                    while(l < r && nums[r+1] == nums[r]) r--;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return ans;
    }
}
