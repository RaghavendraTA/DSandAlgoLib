package org.buildwithraghu.bitwise;

public class MinOpToReduceNumToZero {

    // https://leetcode.com/problems/minimum-operations-to-reduce-an-integer-to-0
    public int minOperations(int n) {
        int res = 0;
        while(n > 0){
            System.out.println(Integer.toBinaryString(n) + " -> " + res);
            if((n & 3) == 3){
                ++n; ++res;
            }
            else{
                res += n & 1;
                n >>= 1;
            }
        }
        System.out.println(Integer.toBinaryString(n) + " -> " + res);
        return res;
    }
}
