package org.buildwithraghu.linkedin;

public class PartitionToKEqualSumSubsets_698 {

    public boolean canPartitionKSubsets(int[] arr, int k) {
        int totalArraySum = 0;
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            totalArraySum += arr[i];
        }
        if (totalArraySum % k != 0) {
            return false;
        }

        int targetSum = totalArraySum / k;
        int[] subsetSum = new int[(1 << n)];
        for (int i = 0; i < (1 << n); ++i) {
            subsetSum[i] = -1;
        }
        subsetSum[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (subsetSum[mask] == -1) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0 && subsetSum[mask] + arr[i] <= targetSum) {
                    subsetSum[mask | (1 << i)] = (subsetSum[mask] + arr[i]) % targetSum;
                }
            }
            if (subsetSum[(1 << n) - 1] == 0) {
                return true;
            }
        }
        return subsetSum[(1 << n) - 1] == 0;
    }
}
