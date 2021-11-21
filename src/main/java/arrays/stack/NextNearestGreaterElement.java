package arrays.stack;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

public class NextNearestGreaterElement {

    public static void replaceWithNextNearestGreaterElement(int[] A) {

        int n = A.length;
        int next, i, j;
        for (i = 0; i < n; i++) {
            next = -1;
            for (j = i + 1; j < n; j++) {
                if (A[i] < A[j]) {
                    next = A[j];
                    break;
                }
            }
            System.out.println(A[i] + " -- " + next);
        }
    }

    public static void main(String[] args) {

        replaceWithNextNearestGreaterElement(new int[]{6, 2, 8, 1, 5, 9});
    }

}
