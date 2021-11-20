package graph.paths;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an initial number x and two operations which are given below:
 *
 * Multiply number by 2.
 * Subtract 1 from the number.
 */

class Pair {
    int value;
    int steps;
    Pair(int left, int right) {
        this.value = left;
        this.steps = right;
    }
}
public class MinOperationRequiredToConvertFromXtoY {

    public static int minOperationRequired(int x, int y) {

        Deque<Pair> queue = new LinkedList<>();
        Pair pair = new Pair(x, 0);
        queue.addLast(pair);

        while (!queue.isEmpty()) {

            Pair temp = queue.poll();

            if (temp.value == y) {
                return temp.steps;
            }

            int mul = temp.value * 2;
            int sub = temp.value - 1;

            if (mul > 0) {
                queue.addLast(new Pair(mul, temp.steps + 1));
            }
            if(sub > 0) {
                queue.addLast(new Pair(sub, temp.steps + 1));
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(minOperationRequired(2, 5));
    }
}
