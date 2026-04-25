package dynamicprogramming.subsequence;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.*;

public class BuildingBridges {

    static void MaxBridgesBuiltBetweenTwoRiver(int[] A, int[] B) {

        // Assuming A is sorted
        // Assuming A and B are unique lists

        int n = B.length;
        Set<Integer> visited = new HashSet<>();

        // Complexity = O(n^2), Space = n + n => O(n)

        Set<Integer> set = new HashSet<>();
        Arrays.stream(A).forEach(set::add);

        int maxCount = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            if (visited.contains(B[i])) {
                continue;
            }
            int counter = 1;
            int prev = B[i];
            for(int j = i + 1; j < n; j++) {
                if (prev < B[j] && set.contains(B[j])) {
                    prev = B[j];
                    counter++;
                    visited.add(B[j]);
                }
            }
            maxCount = Math.max(maxCount, counter);
        }

        System.out.println(maxCount);
    }

    public static void main(String[] args) {
        MaxBridgesBuiltBetweenTwoRiver(new int[]{1, 2, 3, 4}, new int[]{5, 6, 2, 1, 3});
    }
}
