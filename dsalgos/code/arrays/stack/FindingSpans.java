package arrays.stack;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Arrays;
import java.util.Stack;

public class FindingSpans {

    public static int[] findingSpans(int[] A) {

        Stack<Integer> stack = new Stack<>();

        int p, n = A.length;
        int[] s = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A[i] > A[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                p = -1;
            }
            else {
                p = stack.peek();
            }
            s[i] = i - p;
            stack.push(i);
        }

        return s;
    }

    public static void main(String[] args) {
        int[] ans = findingSpans(new int[]{5, 3, 6, 4, 5});
        System.out.println(Arrays.toString(ans));
    }
}
