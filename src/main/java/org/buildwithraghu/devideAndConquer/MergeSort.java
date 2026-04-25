package devideAndConquer;

/*
 * created by raghavendra.ta on 24-Jul-2021
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeSort {

    public void mergeUsingPriorityQueue(int[] A, int start, int mid, int end) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int i = start;
        while(i <= end) {
            queue.add(A[i++]);
        }
        for(i = start; i <= end && !queue.isEmpty(); i++) {
            A[i] = queue.poll();
        }
    }

    public void merge(int[] A, int start, int mid, int end) {

        int[] temp = new int[end - start + 1];
        int i = start, j = mid+1, k = 0;

        while(i <= mid && j <= end) {
            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = A[i++];
        }

        while (j <= end) {
            temp[k++] = A[j++];
        }

        k = 0;
        for(i = start; i <= end; i++) {
            A[i] = temp[k++];
        }
    }

    public void mergeSort(int[] A, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(A, l, m);
            mergeSort(A, m + 1, r);
            merge(A, l, m, r);
        }
    }

    public static void main(String[] args) {
        int[] A = {8, 2, 9, 1, 6, 3, 8, 0, 5};
        new MergeSort().mergeSort(A, 0, A.length-1);
        System.out.println(Arrays.toString(A));
    }
}
