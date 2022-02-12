package google;

import java.util.*;

public class Solution {

    public static long getMinTurns(char src, char dest) {
        return Math.min(
                Math.abs((int)src - (int)dest),
                26 - Math.abs((int)dest - (int)src)
        );
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.next());
        for(int t = 1; t <= T; t++) {
            char[] s = sc.next().toCharArray();
            char[] f = sc.next().toCharArray();
            long sum = 0;
            for (char src : s) {
                long min = Integer.MAX_VALUE;
                for (char dest : f) {
                    min = Math.min(min, getMinTurns(src, dest));
                }
                sum += min;
            }
            System.out.println("Case #" + t + ": " + sum);
        }
    }
}
