package org.buildwithraghu.linkedin;

import java.util.Arrays;

public class CinemaSeatAllocation_1386 {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int ans = 0;
        int prevRow = 0;
        int mask = 0;

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            if (row != prevRow) {
                if (prevRow != 0) {
                    ans += count(mask);
                }
                ans += (row - prevRow - 1) * 2;
                mask = 0;
                prevRow = row;
            }
            if (col >= 2 && col <= 9) {
                mask |= 1 << col;
            }
        }

        if (prevRow != 0) {
            ans += count(mask);
        }
        ans += (n - prevRow) * 2;
        return ans;
    }

    private int count(int mask) {
        boolean left  = (mask & (1<<2 | 1<<3 | 1<<4 | 1<<5)) == 0;
        boolean right = (mask & (1<<6 | 1<<7 | 1<<8 | 1<<9)) == 0;
        boolean mid   = (mask & (1<<4 | 1<<5 | 1<<6 | 1<<7)) == 0;
        if (left && right) return 2;
        if (left || right || mid) return 1;
        return 0;
    }

    static void main() {
        int[][] input = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        CinemaSeatAllocation_1386 c = new CinemaSeatAllocation_1386();
        c.maxNumberOfFamilies(3, input);
    }
}
