package arrays.stack;

/*
 * created by raghavendra.ta on 05-Jul-2021
 */

import java.util.Stack;

/**
 * for i to n
 *      if stack.isEmpty() || stack[top].k > arr[i]
 *          if top > 1: left = stack[top-1].i
 *          else        left = -1
 *      area = (i - left - 1) * stack[top].k
 *      stack.pop()
 *      maxArea = max(area, maxArea)
 *
 *      stack.push(<k, i>)
 */

class Pair {
    public int height;
    public int index;
    Pair(int height, int index) {
        this.height = height;
        this.index = index;
    }
}

/**
 *
 * Base condition at stack is (-1, -1)      i,e {height = -1, index = -1}
 *
 * for i to n:
 *      while (stack[top].height != -1 and stack[top].height >= A[i]:
 *          currentHeight = stack[top].height;
 *          currentWidth = i - stack[top].index - 1;
 *          maxArea = max(maxArea, currentWidth * currentHeight)
 *      stack[top++] = (A[i], i)
 *
 * while (stack[top].height != -1: If any element left in the stack then):
 *      currentHeight = stack[top].height;
 *      currentWidth = stack[top].index;
 *      maxArea = max(maxArea, currentWidth * currentHeight)
 *
 */

public class MaxRectangle {

    static void maxRectangleArea(int[] A) {

        int n = A.length;
        int maxArea = 0;

        Stack<Pair> stack = new Stack<>();

        // Base condition
        stack.push(new Pair(-1, -1));

        for(int i = 0; i < n; i++) {

            while(stack.peek().height != -1 && stack.peek().height >= A[i]) {
                int currentHeight = stack.pop().height;
                int currentWidth = i - stack.peek().index - 1;
                maxArea = Math.max(maxArea, currentHeight * currentWidth);
            }

            stack.push(new Pair(A[i], i));
        }

        while(stack.peek().height != -1) {
            int currentHeight = stack.pop().height;
            int currentWidth = n - stack.peek().index - 1;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);
        }

        System.out.println(maxArea);
    }

    public static void main(String[] args) {
        maxRectangleArea(new int[]{2, 1, 2});
    }
}