package org.buildwithraghu.mapsandsets;

public class StringCompress {

    // https://leetcode.com/problems/string-compression
    public int compress(char[] chars) {
        char lasChar = chars[0];
        int j = 0, c = 1;
        for(int i = 1; i < chars.length; i++) {
            if (chars[i] == lasChar) {
                c++;
            } else {
                chars[j++] = lasChar;
                lasChar = chars[i];
                if (c > 1) {
                    for (char cc : String.valueOf(c).toCharArray())
                        chars[j++] = cc;
                }
                c = 1;
            }
        }
        chars[j++] = lasChar;
        if (c > 1) {
            for (char cc : String.valueOf(c).toCharArray())
                chars[j++] = cc;
        }
        return j;
    }

    public static void main(String[] args) {
        StringCompress sc = new StringCompress();
        char[] input = new char[]{'a','b','b','b'};
        System.out.println(sc.compress(input));
    }
}
