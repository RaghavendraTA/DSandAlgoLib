package org.buildwithraghu.mapsandsets;

import java.util.HashMap;
import java.util.HashSet;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int i: nums) {
            if (!set.add(i))
                return true;
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if (hmap.containsKey(nums[i])) {
                int j = hmap.get(nums[i]);
                if (Math.abs(i-j) <= k) return true;
            }
            hmap.put(nums[i], i);
        }
        return false;
    }
}
