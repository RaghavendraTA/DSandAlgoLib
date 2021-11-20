package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 03-Jul-2021
 */

import java.util.HashMap;
import java.util.Map;

public class LargestSubArray {

    public static void largestSubArraySumToZero(int[] arr) {

        Map<Integer, Integer> occurrence = new HashMap<>();
        int maxLen = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
            if (occurrence.containsKey(arr[i])) {
                maxLen = Math.max(i - occurrence.get(arr[i]), maxLen);
            }
            else {
                occurrence.put(arr[i], i);
            }
        }

        System.out.println(maxLen);
    }

    public static void main(String[] args) {

        int[] arr = new int[]{-1, 2, -6, -4, 10, 0, -11, 11};
        largestSubArraySumToZero(arr);
    }

}
