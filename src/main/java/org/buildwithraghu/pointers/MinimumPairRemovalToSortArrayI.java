package org.buildwithraghu.pointers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumPairRemovalToSortArrayI {

    static class Pair {
        int sum;
        int idx;
        Pair(int sum, int idx) {
            this.idx = idx;
            this.sum = sum;
        }
    }

    boolean isNumsSorted(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] == Integer.MIN_VALUE || nums[i-1] == Integer.MIN_VALUE)
                continue;
            if (nums[i - 1] > nums[i])
                return false;
        }
        return true;
    }

    public int minimumPairRemoval(int[] nums) {
        int ans = 0;
        if (isNumsSorted(nums))
            return ans;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.sum));
        for(int i = 0; i < nums.length - 1; i++) {
            pq.add(new Pair(nums[i] + nums[i + 1], i));
        }
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            nums[p.idx] = p.sum;
            nums[p.idx + 1] = Integer.MIN_VALUE;
            // what about sum at p.idx - 1 ?

        }
        return -1;
    }

    public static void main(String[] args) {
        MinimumPairRemovalToSortArrayI m = new MinimumPairRemovalToSortArrayI();
        boolean ans = m.isNumsSorted(new int[]{1, 8, 2, 3, 4});
        System.out.println(ans);
    }
}
