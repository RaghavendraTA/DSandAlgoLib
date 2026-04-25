package slidingwindow;

import java.util.*;

public class MinSwap {

    // Min swap needed to consecutive swaps for the 1's to group of 'K'
    // As it's 1's we can use prefixSum
    public int minMoves(int[] nums, int k) {
        if(k <= 1) return 0;
        List<Integer> ones = new ArrayList<>();
        ones.add(0);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) ones.add(i);
        }
        int[] prefix = new int[ones.size()];
        for(int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i-1] + ones.get(i);
        }
        int ans = Integer.MAX_VALUE;
        int radius = (k-1)/2;
        for(int i = 1; i <= ones.size() - k; i++) {
            /*
             * Left Boundary -> i
             * Right Boundary -> i + k - 1
             * Mid Index = i + radius
             */
            int midIndex = i + radius;
            int leftCost = prefix[midIndex-1] - prefix[i-1];
            int rightCost = prefix[i + k - 1] - prefix[midIndex];
            int totalCost = rightCost - leftCost - ((k % 2 == 0) ? ones.get(midIndex) : 0);
            ans = Math.min(ans, totalCost);
        }
        ans -= radius*(radius+1);
        if(k % 2 == 0) ans -= k/2;
        return ans;
    }
}
