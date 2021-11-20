package sorting;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Arrays;

public class MergeSort {

    public static void merge(int[] A, int left, int mid, int right) {
        int[] temp = new int[A.length];
        int k = 0;
        int i = left, iEnd = mid - 1, j = mid;
        while(i <= iEnd && j <= right) {
            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            }
            else {
                temp[k++] = A[j++];
            }
        }
        while(i <= iEnd) {
            temp[k++] = A[i++];
        }
        while(j <= right) {
            temp[k++] = A[j++];
        }
        for(i = left, j = 0; i <= right && j < k; i++, j++)
            A[i] = temp[j];
    }

    public static void mergeSort(int[] A, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (right + left) / 2;
        mergeSort(A, left, mid);
        mergeSort(A, mid + 1, right);
        merge(A, left, mid + 1, right);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 3, 7, 3, 7, 1, 9, 0};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
