package airthmetic;

/*
 * created by raghavendra.ta on 15-Jul-2021
 */

import java.util.ArrayList;
import java.util.List;

/**
 *  input List digits = [1, 2, 3...n]
 *
 *  while n > 0:
 *
 *      # base condition if no element left, i.e: n == 0 just pick 1 element from the digits'
 *      ans.append(digits[0]);
 *
 *      # find which quadrant from digits has to be picked
 *      quadrant = k / fact(n-1)
 *
 *      # condition if the expected quadrant is at the edge of the section then we decrement
 *      if (k % fact[n - 1] == 0) quadrant--
 *
 *      ans.append(digits[quadrant]);
 *      remove from list digits[quadrant]
 *
 *      # compute next k to be checked for
 *      k = k - fact[n-1] * quadrant
 *      n--;
 *
 * return ans
 *
 * Video Ref: https://www.youtube.com/watch?v=W9SIlE2jhBQ
 *
 */
public class KthPermutation {

    public static int[] calc_fact() {
        int[] fact = new int[10];
        fact[0] = 0;
        fact[1] = 1;
        for (int i = 2; i <= 9; i++) {
            fact[i] = fact[i - 1] * i;
        }
        return fact;
    }

    public static List<String> getNumArray(int n) {
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            result.add(String.valueOf(i));
        }
        return result;
    }

    public static String getPermutation(int n, int k) {

        int[] fact = calc_fact();
        List<String> digits = getNumArray(n);
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            if (n == 1) {
                ans.append(digits.get(0));
                break;
            }
            int quadrant = k / fact[n - 1];
            if (k % fact[n - 1] == 0) {
                quadrant--;
            }
            ans.append(digits.get(quadrant));
            digits.remove(quadrant);
            k = k - fact[n - 1] * quadrant;
            n--;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(4, 11));
    }

}
