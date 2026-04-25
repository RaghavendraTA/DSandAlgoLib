package org.buildwithraghu.stack;

import java.util.*;

// https://leetcode.com/problems/daily-temperatures/
public class DailyTemperature {

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        // ArrayDeque is much faster than actual Stack class
        Deque<int[]> stk = new ArrayDeque<>();
        int[] ans = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while(!stk.isEmpty() && temperatures[i] >= stk.getLast()[0])
                stk.pollLast();
            if (!stk.isEmpty())
                ans[i] = stk.getLast()[1] - i;
            stk.offerLast(new int[]{temperatures[i], i});
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] temp = new int[]{73,74,75,71,69,72,76,71,73};
        DailyTemperature dt = new DailyTemperature();
        System.out.println(Arrays.toString(dt.dailyTemperatures(temp)));
    }
}
