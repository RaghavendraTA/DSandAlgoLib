package airthmetic;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

/**
 *
 * Iterate to the left in array till we reach the condition (base i = string.len - 2)
 *              A[i] < A[i+1]   i.e (i--)
 *
 * again Iterate to the left in array where we reach the condition (loop till j > i)
 *              A[j] > A[i] i.e (j--)
 *
 * swap A[i] <=> A[j]
 *
 * reverse A(i+1, n)
 *
 * Ref: https://leetcode.com/problems/next-permutation/solution/
 */
public class NextPermutation {

    void reverseArray(int arr[], int start, int end) {

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public void nextPermutation(int[] numbs) {

        int i = numbs.length - 2;

        while (i >= 0 && numbs[i + 1] <= numbs[i]) {
            i--;
        }

        if (i >= 0) {
            int j = numbs.length - 1;
            while (numbs[j] <= numbs[i]) {
                j--;
            }

            int temp = numbs[i];
            numbs[i] = numbs[j];
            numbs[j] = temp;
        }

        reverseArray(numbs, i + 1, numbs.length - 1);
    }
}
