package org.buildwithraghu.sorting;

import arrays.sort.ISortingAlgo;

import java.util.Random;

public class BubbleSort implements ISortingAlgo {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for(int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(0, 10);
        }
        new BubbleSort().sort(arr);
        for(int i: arr) {
            System.out.print(i + ", ");
        }
    }
}
