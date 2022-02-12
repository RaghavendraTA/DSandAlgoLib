package slidingwindow;

import java.util.*;

/**
 * An integer K and a non-empty array A consisting of N integers are given.
 *
 * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
 *
 * A bounded slice is a slice in which the difference between the maximum and minimum values in the slice is less than or equal to K. More precisely it is a slice, such that max(A[P], A[P + 1], ..., A[Q]) − min(A[P], A[P + 1], ..., A[Q]) ≤ K.
 *
 * The goal is to calculate the number of bounded slices.
 *
 * For example, consider K = 2 and array A such that:
 *
 *     A[0] = 3
 *     A[1] = 5
 *     A[2] = 7
 *     A[3] = 6
 *     A[4] = 3
 * There are exactly nine bounded slices: (0, 0), (0, 1), (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (3, 3), (4, 4).
 *
 * Write a function:
 *
 * class Solution { public int solution(int K, int[] A); }
 *
 * that, given an integer K and a non-empty array A of N integers, returns the number of bounded slices of array A.
 *
 * If the number of bounded slices is greater than 1,000,000,000, the function should return 1,000,000,000.
 *
 * For example, given:
 *
 *     A[0] = 3
 *     A[1] = 5
 *     A[2] = 7
 *     A[3] = 6
 *     A[4] = 3
 * the function should return 9, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * K is an integer within the range [0..1,000,000,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 */

public class BoundedSlice {

    private PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minQueue = new PriorityQueue<>();

    public void set(int[] A, int i) {
       if (i < A.length) {
           maxQueue.add(A[i]);
           minQueue.add(A[i]);
       }
    }

    public void remove(int[] A, int i) {
        if (i < A.length) {
            maxQueue.remove(A[i]);
            minQueue.remove(A[i]);
        }
    }

    public int solution(int K, int[] A) {
        maxQueue.clear();
        minQueue.clear();
        if (A.length == 1) {
            return 1;
        }
        int i = 0, j = 0, ans = 0, MAX = 1000000000;
        set(A, 0);
        while(i < A.length && j < A.length) {
            if (ans + A.length >= MAX) {
                return MAX;
            }
            if (i == j) {
                j++;
                set(A, j);
            }
            else if ((maxQueue.peek() - minQueue.peek()) <= K) {
                ans++;
                j++;
                set(A, j);
            }
            else {
                remove(A, i);
                i++;
            }
        }
        while(i < A.length) {
            if ((maxQueue.peek() - minQueue.peek()) <= K) {
                ans++;
            }
            i++;
            remove(A, i);
        }
        return ans + A.length;
    }

    public static void main(String[] args) {
        int ans = new BoundedSlice().solution(2, new int[]{3, 5});
        System.out.println(ans);
    }
}
