package hackerearth;

/*
 * created by raghavendra.ta on 16-Jan-2022
 */

import java.util.*;

public class BeautifulNumbers {

    public static boolean isBeautiful(long n) {
        int cnt = 60;
        while(cnt > 0) {
            int sum = 0;
            while(n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
            if (n == 1) return true;
            cnt--;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int[] range = new int[1000000 + 1];
        range[1] = 1;
        for(long i = 2; i <= 1000000; i++) {
            int index = (int) i;
            range[index] = range[index-1];
            if (isBeautiful(i)) {
                range[index] += index;
            }
        }

        for(int i = 0; i < n; i++) {
            long l = sc.nextLong();
            long r = sc.nextLong();
            System.out.println(range[(int)r] - range[(int)l-1]);
        }
    }
}
