package geeksforgeeks.array;

import java.util.Scanner;
import java.util.Stack;

public class WaveArray {

    public static void convertToWave(int arr[], int n){

        for(int i = 0; i < n - 1; i++) {
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
            i++;
        }
    }

    public static void printArray(int[] arr) {
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void rotateArray(int[] arr, int n, int d) {
        d = d % n;
        if (d == 0) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < d; i++) {
            stack.push(arr[i]);
        }
        for(int i = d; i < n; i++) {
            arr[i-d] = arr[i];
        }
        d = n - d;
        for(int i = n - 1; i >= d; i--) {
            arr[i] = stack.pop();
        }
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int d = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            rotateArray(arr, n, d);
            printArray(arr);
        }
    }
}
