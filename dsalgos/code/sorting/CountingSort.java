package sorting;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

public class CountingSort {

    public static void countingSort(int[] A, int k, int[] B) {
        int[] c = new int[k];
        int n = A.length;

        for(int i = 0; i < k; i++) {
            c[i] = 0;
        }

        for (int value : A) {
            c[value] = c[value] + 1;
        }

        for(int i = 1; i < k; i++) {
            c[i] = c[i] + c[i-1];
        }

        for(int j = n-1; j >= 0; j--) {
            B[c[A[j]]] = A[j];
            c[A[j]] = c[A[j]] - 1;
        }
    }

}
