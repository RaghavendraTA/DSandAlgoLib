package org.buildwithraghu.pointers;

import java.util.*;

public class PairsOfDiffK {
    public int countKDifference(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for(int i: nums) {
            map.merge(i, 1, Integer::sum);
        }
        for(Map.Entry<Integer, Integer> e: map.entrySet()) {
            int c = e.getValue(), diffKey = Math.abs(k+e.getKey());
            if (e.getKey() != diffKey && map.containsKey(diffKey)) {
                ans += c * map.get(diffKey);
            }
        }
        return ans;
    }

    public int countKDifference_0ms(int[] nums, int k) {
        int ans = 0;
        int[] count = new int[101];
        for(int i: nums) {
            count[i]++;
        }
        for(int i = k + 1; i <= 100; i++) {
            ans += count[i] * count[i-k];
        }
        return ans;
    }

    public static void main(String[] args) {
        PairsOfDiffK p = new PairsOfDiffK();
        p.countKDifference(new int[]{3,2,1,5,4}, 2);
    }
}
