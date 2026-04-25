package org.buildwithraghu.bitwise;

import java.util.HashSet;

public class XORofTwiceAppeared {
    public int duplicateNumbersXOR(int[] nums) {
        HashSet<Integer> hset = new HashSet<>();
        int ans = 0;
        for(int i: nums) {
            hset.add(i);
            ans ^= i;
        }
        for(int i: hset) {
            ans ^= i;
        }
        return ans;
    }
}
