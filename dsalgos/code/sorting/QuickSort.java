package sorting;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Arrays;

public class QuickSort {

    public static int partition(int[] A, int low, int high) {
        int pivot = A[low];
        int left = low, right = high;

        while(left < right) {
            while(A[left] <= pivot) {
                left++;
            }
            while(A[right] > pivot) {
                right--;
            }
            if (left < right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }

        A[low] = A[right];
        A[right] = pivot;
        return right;
    }

    public static void quickSort(int[] A, int low, int high) {
        if (low < high) {
            int pivot = partition(A, low, high);
            quickSort(A, low, pivot - 1);
            quickSort(A,  pivot + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 3, 7, 3, 7, 1, 9, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
