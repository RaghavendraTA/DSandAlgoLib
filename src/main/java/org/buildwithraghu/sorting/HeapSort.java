package org.buildwithraghu.sorting;

import java.util.Random;

public class HeapSort {

    // Max Heap implementation
    public static void heapify(int[] arr, int n, int i) {
        int large = i, t;
        int lc = (i * 2) + 1;
        int rc = (i * 2) + 2;
        if (lc < n && arr[lc] > arr[large])
           large = lc;
        if (rc < n && arr[rc] > arr[large])
            large = rc;
        if (large != i) {
            t = arr[i];
            arr[i] = arr[large];
            arr[large] = t;
            heapify(arr, n, large);
        }
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Moving current root to end
        for (int i = n - 1; i > 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;

            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(0, 100);
        }
        sort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }
}
