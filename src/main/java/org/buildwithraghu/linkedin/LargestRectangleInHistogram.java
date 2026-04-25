package org.buildwithraghu.linkedin;

import java.util.Stack;

public class LargestRectangleInHistogram {

    class Pair {
        public int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(-1, -1));
        int n = heights.length, maxArea = 0;
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && heights[i] <= stack.peek().first) {
                int height = stack.pop().first;
                int pos = stack.peek().second;
                maxArea = Math.max(maxArea, (i - pos - 1) * height);
            }
            stack.push(new Pair(heights[i], i));
        }
        while(stack.peek().first != -1) {
            int height = stack.pop().first;
            int pos = stack.peek().second;
            maxArea = Math.max(maxArea, (n - pos - 1) * height);
        }
        return maxArea;
    }
}
