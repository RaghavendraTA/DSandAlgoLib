package sorting;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Arrays;

public class N2Sorting {

    public static void bubbleSort(int[] A) {
        int n = A.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (A[j] > A[j + 1]) {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        int min, n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int v = arr[i];
            int j = i;
            while (j >= 1 && arr[j - 1] > v) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = v;
        }
    }

    public static void shellSorting(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{6, 3, 7, 3, 7, 1, 9, 0};
        shellSorting(arr);
        System.out.println(Arrays.toString(arr));
    }
}
