package sorting;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Arrays;

public class SumOfPairCloseToZero {

    public static void execute(int[] A) {
        Arrays.sort(A);
        int i = 0, j = A.length - 1, positiveClosest = Integer.MAX_VALUE, negativeCloses = Integer.MIN_VALUE;
        while(i < j) {
            int temp = A[i] + A[j];
            if (temp > 0) {
                if (temp < positiveClosest)
                    positiveClosest = temp;
                j--;
            }
            else if (temp < 0) {
                if (temp > negativeCloses)
                    negativeCloses = temp;
                i++;
            }
            else
                System.out.println("sum = " + A[i] + A[j]);
        }
    }
}
