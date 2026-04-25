package org.buildwithraghu.dynamicprogramming;

public class CountNoOfHouses {

    // https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i
    public int[] countOfPairs(int n, int x, int y) {
        // to make sure that x<=y in every case
        if (x > y)
            return countOfPairs(n, y, x);

        int[] res = new int[n];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int idx = i - j;
                idx = Math.min(idx, Math.abs(j - x) + 1 + Math.abs(i - y));
                res[idx - 1] += 2; // add 2 as we can go from j->i and i->j
            }
        }
        return res;
    }
}
