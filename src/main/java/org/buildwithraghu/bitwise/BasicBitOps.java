package org.buildwithraghu.bitwise;

public class BasicBitOps {

    public static int setIthBit(int n, int i) {
        int mask = 1<<1;
        return n | mask;
    }

    public static int clearIthBit(int n, int i) {
        int mask = ~(1<<i);
        return n & mask;
    }

    public static int flipIthBit(int n, int i) {
        int mask = 1<<i;
        return n^mask;
    }

    public static int countBits(int n) {
        int c = 0;
        while(n > 0) {
            c += (n&1);
            n = n >> 1;
        }
        return c;
    }

    public static void main(String[] args) {

        System.out.println(countBits(10)); // 1010
        System.out.println(clearIthBit(7, 1)); // 111 -> 101
        System.out.println(setIthBit(5, 1)); // 101 -> 111
        System.out.println(flipIthBit(4, 0)); // 100 -> 111
        System.out.println(flipIthBit(6, 2)); // 110 -> 010

        System.out.println("-----------------------------------");
        System.out.println(Integer.bitCount(15)); // 1111
        System.out.println(Long.bitCount(123456789012345L)); // 1111

        System.out.println("-----------------------------------");
        // for 1010 highest bit is at 3rd from left, so make rest everything 0
        // 1000 is the answer -> 8
        System.out.println(Integer.highestOneBit(10));
    }
}
