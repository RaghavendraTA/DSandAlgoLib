package airthmetic;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class ReplaceValueWIthProductOfOthers {

    static void ReplaceElements(int arr[], int n) {

        int prod = 1;

        // Calculate the product of all the elements
        for (int i = 0; i < n; ++i) {
            prod *= arr[i];
        }

        // Replace every element product
        // of all other elements
        for (int i = 0; i < n; ++i) {
            arr[i] = prod / arr[i];
        }
    }

    public static void main(String[] args) {

        int arr[] = {2, 3, 3, 5, 7};
        int n = arr.length;

        ReplaceElements(arr, n);

        // Print the modified array.
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");

    }
}
