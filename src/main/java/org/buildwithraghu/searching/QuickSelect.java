package org.buildwithraghu.searching;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class QuickSelect {

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l;
        for(int j = l; j < r; j++) {
            if (arr[j] < pivot) {
                int t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
                i++;
            }
        }
        int t = arr[i];
        arr[i] = arr[r];
        arr[r] = t;
        return i;
    }

    public static int kthSmallest(int[] arr, int l, int r, int k) {
        if (l == r)
            return arr[l];
        int p = partition(arr, l, r);
        if (k == p)
            return arr[k];
        if (k < p)
            return kthSmallest(arr, l, p-1, k);
        return kthSmallest(arr, p+1, r, k);
    }

    public static int kthSmallUsingPQ(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i: arr) {
            pq.offer(i);
        }
        for(int i = 1; i < k; i++) {
            pq.poll();
        }
        if (pq.isEmpty()) return -1;
        return pq.peek();
    }

    public static int kthLargestUsingPQ(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((p1, p2) -> p2-p1);
        // can also use Collections.reverseOrder();
        for(int i: arr) {
            pq.offer(i);
        }
        for(int i = 1; i < k; i++) {
            pq.poll();
        }
        if (pq.isEmpty()) return -1;
        return pq.peek();
    }

    public static void main(String[] args) {
        Random random = new Random();
        int MAX = 30, k = 5;
        int[] arr = new int[MAX];
        for(int i = 0; i < MAX; i++) {
            arr[i] = random.nextInt(0, (100*MAX));
        }

        int[] copy = new int[MAX];
        System.arraycopy(arr, 0, copy, 0, MAX);

        System.out.println("QuickSelect  " + k + " = " + QuickSelect.kthSmallest(arr, 0, MAX-1, k-1));
        System.out.println("Priority Q   " + k + " = " + kthSmallUsingPQ(copy, k));

        Arrays.sort(copy);
        System.out.println("Sort & Get   " + k + " = " + copy[k-1]);

        for(int i = 0; i < k; i++)
            System.out.print(copy[i] + ", ");
    }
}
