package dynamicprogramming.choosebest;

/*
 * created by raghavendra.ta on 29-Jun-2021
 */

/**
 * n is number of nodes, 1 ... n can form number of BinarySearchTrees
 * Calculate the possible BSTs that can be formed using n
 * <p>
 * Solution: Summation(C[i-1] x C[n-i]) where ith is some middle element.
 * i vary from 1 to n;
 */

public class BSTsUsingNumber {

    public static int catalanNumber(int n) {

        if (n == 0) {
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += catalanNumber(i - 1) * catalanNumber(n - i);
        }
        return count;
    }

    public static int[] table = new int[1024];

    public static int catalanNumberUsingMemoization(int n) {

        if (table[n] != 1) {
            return table[n];
        }
        table[n] = 0;
        for (int i = 1; i <= n; i++) {
            table[n] += catalanNumberUsingMemoization(i - 1) * catalanNumberUsingMemoization(n - i);
        }
        return table[n];
    }

    public static double fact(double n) {
        if (n == 0) return 1;
        double f = 1;
        for(double i = n; i > 0; i--) {
            f *= i;
        }
        return f;
    }

    public static double equation(double n) {
        return fact(2 * n) / (fact(n) * fact(n + 1));
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 19; i++) {
            //System.out.println(catalanNumberUsingMemoization(i));
            System.out.println(catalanNumber(i));
            System.out.println((int)Math.ceil(equation(i)));
        }
    }
}
