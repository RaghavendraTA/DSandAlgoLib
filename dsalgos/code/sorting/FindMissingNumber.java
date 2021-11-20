package sorting;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

public class FindMissingNumber {

    public static int usingEquation(int[] arr, int n) {
        int sum = (n * (n + 1)) / 2;
        for(int i: arr)
            sum -= i;
        return sum;
    }

    public static int usingXOR(int[] arr, int n) {
        int x = 0, y = 0;
        for(int i: arr)
            x ^= i;
        for(int i = 1; i <= n; i++)
            y ^= i;
        return x ^ y;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 5, 6, 7, 8};
        System.out.println(usingEquation(arr, 8));
        System.out.println(usingXOR(arr, 8));
    }
}
