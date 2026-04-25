package org.buildwithraghu.sorting;

import java.util.Random;

public class QuickSort {

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l+1;
        for(int j = l+1; j <= r; j++) {
            if (arr[j] < pivot) {
                int t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
                i++;
            }
        }
        arr[l] = arr[i - 1];
        arr[i - 1] = pivot;

        return i - 1;
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        quickSort(arr, l, p-1);
        quickSort(arr, p+1, r);
    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length-1);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        int newInt = 15;
        for(int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(0, 1000);//newInt--;
        }
        sort(arr);
        for(int i: arr) {
            System.out.print(i + ", ");
        }
    }
}
