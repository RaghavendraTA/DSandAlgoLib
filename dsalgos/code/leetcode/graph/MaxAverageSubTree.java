package leetcode.graph;

/*
 * created by raghavendra.ta on 18-Jul-2021
 */

import trees.binarytrees.BinaryNode;

public class MaxAverageSubTree {

    static class Pair {
        int count;
        int sum;
        Pair(int count, int sum) {
            this.count = count;
            this.sum = sum;
        }
    }

    private double ans = 0.0;

    public Pair traverse(BinaryNode<Integer> root) {

        if (root.left == null && root.right == null) {
            ans = Math.max(ans, root.value);
            return new Pair(1, root.value);
        }

        Pair left = new Pair(0, 0), right = new Pair(0, 0);

        if (root.left != null) {
            left = traverse(root.left);
        }
        if (root.right != null) {
            right = traverse(root.right);
        }

        int sum = left.sum + right.sum + root.value;
        int count = left.count + right.count + 1;
        double avg = ((double) sum) / ((double) count);

        ans = Math.max(ans, avg);
        return new Pair(count, sum);
    }

    public double maximumAverageSubtree(BinaryNode<Integer> root) {
        if (root != null)
            traverse(root);
        return ans;
    }
}
