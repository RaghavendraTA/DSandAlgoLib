package org.buildwithraghu.dynamicprogramming;

import java.util.HashMap;

public class DeleteAndEarn {

    // Top-down approach
    HashMap<Integer, Integer> points = new HashMap<>();
    HashMap<Integer, Integer> memo = new HashMap<>();

    private int maxPoints(int num) {
        if (num == 0)
            return 0;
        if (num == 1)
            return points.getOrDefault(1, 0);

        if (memo.containsKey(num))
            return memo.get(num);

        int gain = points.getOrDefault(num, 0);
        memo.put(num, Math.max(maxPoints(num-1), maxPoints(num-2) + gain));
        return memo.get(num);
    }

    public int deleteAndEarn_memo(int[] nums) {
        int maxNumber = 0;

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        return maxPoints(maxNumber);
    }

    // bottom-up approach
    // here [2, 2, 2] means => (2 + 2 + 2) total points
    public int deleteAndEarn(int[] nums) {
        // stream is little slow
        // int maxNumber = Arrays.stream(nums).max().getAsInt();
        int maxNumber = Integer.MIN_VALUE;
        for (int num : nums)
            maxNumber = Math.max(maxNumber, num);
        int[] points = new int[maxNumber+1];
        for (int num : nums) {
            points[num] += num;
        }

        int[] maxPoints = new int[maxNumber+1];
        maxPoints[1] = points[1];
        for(int i = 2; i <= maxNumber; i++) {
            maxPoints[i] = Math.max(maxPoints[i-1], maxPoints[i-2] + points[i]);
        }
        return maxPoints[maxNumber];
    }
}
