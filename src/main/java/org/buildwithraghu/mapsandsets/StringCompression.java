package org.buildwithraghu.mapsandsets;

public class StringCompression {

    // https://leetcode.com/problems/string-compression
    public int compress(char[] chars) {
        int freq = 1, k = 0;
        char lastChar = chars[0];
        for(int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i-1])
                freq++;
            else {
                chars[k++] = lastChar;
                lastChar = chars[i];
                if (freq > 1) {
                    for (char c : String.valueOf(freq).toCharArray()) {
                        chars[k++] = c;
                    }
                }
                freq = 1;
            }
        }
        chars[k++] = lastChar;
        if (freq > 1) {
            for (char c : String.valueOf(freq).toCharArray()) {
                chars[k++] = c;
            }
        }
        return k;
    }
}
