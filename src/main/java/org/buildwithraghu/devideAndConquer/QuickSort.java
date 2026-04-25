package devideAndConquer;

/*
 * created by raghavendra.ta on 25-Jul-2021
 */

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    // Partition Logic
    public int partition(int[] A, int pivot, int l, int r) {

        int left = l, right = r;

        while(left <= right) {

            while(A[left] < pivot) {
                left++;
            }
            while(A[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
                left++;
                right--;
            }
        }

        return left;
    }

    // Partition using Mid Element
    public int partitionUsingMid(int[] A, int l, int r) {
        int pivot = A[(l + r) / 2];
        return partition(A, pivot, l, r);
    }

    // Partition using Random Index
    public int partitionUsingRandomIndex(int[] A, int l, int r) {
        int pivot = A[new Random().nextInt(r-l) + l];
        return partition(A, pivot, l, r);
    }

    public void quickSort(int[] A, int l, int r) {
        if (l < r) {
            int p = partitionUsingRandomIndex(A, l, r);
            quickSort(A, l, p - 1);
            quickSort(A, p, r);
        }
    }

    public static void main(String[] args) {
        int[] A = {8, 2, 9, 1, 6, 3, 8, 0, 5};
        new QuickSort().quickSort(A, 0, A.length-1);
        System.out.println(Arrays.toString(A));
    }
}
