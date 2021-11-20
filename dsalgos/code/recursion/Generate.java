package recursion;

/*
 * created by raghavendra.ta on 22-Jun-2021
 */

import java.util.Arrays;

public class Generate {

    /**
     * Generate all the strings of n bits
     * Assume bitsString[0...n-1] is an array of n
     */
    public static int[] bitsString;

    public static void nBits(int n) {
        bitsString = new int[n];
        nBits(0, n);
    }

    private static void nBits(int i, int n) {
        if (i >= n)
            System.out.println(Arrays.toString(bitsString));
        else {
            bitsString[i] = 0;
            nBits(i + 1, n);
            bitsString[i] = 1;
            nBits(i + 1, n);
        }
    }
}
