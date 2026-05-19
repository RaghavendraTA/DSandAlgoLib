package org.buildwithraghu.hackerrank;

import java.io.*;
import java.util.stream.*;

class Result {

    private static long highestBit(long n) {
        long res = 1;
        while (res <= n) {
            res <<= 1;
        }
        return res >> 1;
    }

    public static String counterGame(long n) {
        // Write your code here
        boolean flag = true;
        while(n > 1) {
            long i = highestBit(n);
            if (i == n) {
                n /= 2;
            } else {
                n -= i;
            }
            flag = !flag;
        }
        return flag ? "Louise" : "Richard" ;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(Result.counterGame(132));
    }
}
