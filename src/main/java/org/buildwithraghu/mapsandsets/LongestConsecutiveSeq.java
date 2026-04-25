package org.buildwithraghu.mapsandsets;

import java.util.Arrays;
import java.util.HashMap;

public class LongestConsecutiveSeq {

    // O(n) supposed to be faster than below answer
    public int longestConsecutive_usingMap(int[] nums) {
        HashMap<Integer, Boolean> hmap = new HashMap<>();
        int maxLen = 0;
        for(int i: nums) hmap.put(i, false);
        for(int i: nums) {
            if (hmap.get(i) == true) continue;
            int f = i + 1, p = i - 1, d;
            while(hmap.containsKey(p)) {
                hmap.put(p, true);
                p--;
            }
            while(hmap.containsKey(f)) {
                hmap.put(f, true);
                f++;
            }
            p++;
            f--;
            if (p < 0) d = Math.abs(p) + f + 1;
            else d = f-p+1;
            maxLen = Math.max(maxLen, d);
        }
        return maxLen;
    }

    // Using Sort()
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int maxLen = 1, d = 1;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++) {
            if (nums[i+1] == nums[i])
                continue;
            if (nums[i+1] == nums[i]+1)
                d++;
            else
                d = 1;
            maxLen = Math.max(maxLen, d);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestConsecutiveSeq ls = new LongestConsecutiveSeq();
        System.out.println(ls.longestConsecutive(new int[]{1, 0, 1, 2}));
    }
}
