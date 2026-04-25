package org.buildwithraghu.sorting;

import arrays.sort.ISortingAlgo;

public class MergeSort implements ISortingAlgo {

    private static void merge(int[] arr, int l, int m, int r) {
        int i = l, j = m + 1, k = 0, len = r - l + 1;
        int[] t = new int[len];
        while (i <= m && j <= r) {
            t[k++] = arr[j] <= arr[i] ? arr[j++] : arr[i++];
        }
        while (i <= m) {
            t[k++] = arr[i++];
        }
        while (j <= r) {
            t[k++] = arr[j++];
        }
        System.arraycopy(t, 0, arr, l, len);
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        // Random random = new Random();
        int[] arr = new int[10];
        int newInt = 15;
        for (int i = 0; i < 10; i++) {
            arr[i] = newInt--;
        }
        new MergeSort().sort(arr);
        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }
}
