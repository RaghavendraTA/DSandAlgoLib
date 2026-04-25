package arrays.stack;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Stack;

public class StackPermutation {

    // Check a List can be formed using another list of numbers by doing
    // Only stack operations.

    static boolean checkStackPermutation(int A[], int B[]) {

        int n = A.length;
        int i = 0, j = 0;

        Stack<Integer> tempStack = new Stack<>();

        while (i < n) {
            int ele = A[i++];

            if (ele == B[j]) {
                j++;
                while (!tempStack.isEmpty() && tempStack.peek() == B[j]) {
                    tempStack.pop();
                    System.out.print("X");
                    j++;
                }
            }
            else {
                tempStack.push(ele);
                System.out.print("S");
            }
        }
        return tempStack.isEmpty();
    }

    public static void main(String[] args) {
        boolean check = checkStackPermutation(new int[]{1, 2, 3, 4, 5, 6}, new int[]{1, 5, 4, 6, 2, 3});
        System.out.println("\n" + check);
        check = checkStackPermutation(new int[]{1, 2, 3, 4, 5, 6}, new int[]{3, 2, 5, 6, 4, 1});
        System.out.println("\n" + check);
    }
}
