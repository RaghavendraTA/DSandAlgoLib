package utilities;

/*
 * created by raghavendra.ta on 11-Oct-2021
 */

import java.util.Arrays;

public class ArrayUtils {

    public static <T> void fill(T[][] array, T value) {
        for (final T[] arr : array) {
            Arrays.fill(arr, value);
        }
    }

    public static <T> void print2DArray(T[][] array) {
        System.out.println();
        int len = getMax(array);
        int rowLen = (array.length + "").length();
        System.out.print(" ".repeat(rowLen) + " | ");
        for(int i = 0; i < array[0].length; i++) {
            String v = i + "";
            int slen = len - v.length();
            System.out.print(" ".repeat(Math.max(0, slen)) + v + ", ");
        }
        System.out.println("\b\b");
        System.out.println("-".repeat(((len + 2) * array[0].length) + rowLen + 3));
        for (int i = 0; i < array.length; i++) {
            int slen = rowLen - (i + "").length();
            System.out.print(" ".repeat(Math.max(0, slen)) + i + " | ");
            for (final T t : array[i]) {
                String v = t.toString();
                slen = len - v.length();
                System.out.print(" ".repeat(Math.max(0, slen)) + v + ", ");
            }
            System.out.println("\b\b");
        }
    }

    private static <T> int getMax(T[][] array) {
        int mx = array[0][0].toString().length();
        for(T[] arr: array) {
            for(T v: arr) {
                mx = Math.max(mx, v.toString().length());
            }
        }
        return mx;
    }

    public static <T> void copyArray(T[][] src, T[][] dest) {
        for(int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }
    }
}
