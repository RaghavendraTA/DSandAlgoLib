package org.buildwithraghu.mapsandsets;

public class IntegerToRomanViceVersa {

    static int[] nos = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    static String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    // https://leetcode.com/problems/integer-to-roman/
    public String intToRoman(int num) {
        int i = 0;
        StringBuilder b = new StringBuilder();
        while(num > 0) {
            if (num < nos[i]) {
                i++;
                continue;
            }
            int d = num / nos[i];
            b.append(String.valueOf(roman[i]).repeat(Math.max(0, d)));
            num = num % nos[i];
        }
        return b.toString();
    }

    // -----------------------------------------------------------------------------------------------
    static int[] freq = new int[128];
    static {
        freq['I'] = 1;
        freq['V'] = 5;
        freq['X'] = 10;
        freq['L'] = 50;
        freq['C'] = 100;
        freq['D'] = 500;
        freq['M'] = 1000;
    }

    // https://leetcode.com/problems/roman-to-integer
    public int romanToInt(String s) {
        int res = 0, i = 0, ci, ci1;
        for(; i < s.length() - 1; i++) {
            ci = freq[s.charAt(i)];
            ci1 = freq[s.charAt(i+1)];
            if (ci1 > ci) {
                res += ci1 - ci;
                i++;
            } else {
                res += ci;
            }
        }
        if (i < s.length()) {
            res += freq[s.charAt(i)];
        }
        return res;
    }
}
