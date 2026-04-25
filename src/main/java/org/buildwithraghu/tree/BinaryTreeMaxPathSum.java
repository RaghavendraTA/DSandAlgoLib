package org.buildwithraghu.tree;

public class BinaryTreeMaxPathSum {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int maxSum = Integer.MIN_VALUE;

    // https://leetcode.com/problems/binary-tree-maximum-path-sum
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        // only take positive contributions
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        // best path passing through this node
        int localMax = node.val + left + right;

        // update global max
        maxSum = Math.max(maxSum, localMax);

        // return best single-path (can't take both left & right upwards)
        return node.val + Math.max(left, right);
    }
}
