package devideAndConquer;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

public class MedianInTwoSortedArray {

    static double median(int[] A, int[] B) {

        int m = A.length, n = B.length;

        if (A.length == 0 && B.length == 0) {
            return -1;
        }

        if (B.length < A.length) {
            int[] t = A;
            A = B;
            B = t;
        }

        int l = 0, r = A.length;

        while (l <= r) {

            int i = (l + r) / 2;
            int j = (m + n + 1) / 2 - i;

            int Aleft = i == 0 ? Integer.MIN_VALUE : A[i - 1];
            int Aright = i == m ? Integer.MAX_VALUE : A[i];
            int Bleft = j == 0 ? Integer.MIN_VALUE : B[j - 1];
            int Bright = j == n ? Integer.MAX_VALUE : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                } else {
                    return Math.max(Aleft, Bleft);
                }
            }
            else if (Aleft > Bright) {
                r = i - 1;
            }
            else {
                l = i + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println(median(new int[]{}, new int[]{1, 2, 3, 4, 5}));
    }
}
