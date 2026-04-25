package org.buildwithraghu.bitwise;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int a = 0;
        for(int i: nums) {
            a ^= i;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 2, 7, 6, 2, 1};
        SingleNumber s = new SingleNumber();
        System.out.println(s.singleNumber(arr));
    }
}
