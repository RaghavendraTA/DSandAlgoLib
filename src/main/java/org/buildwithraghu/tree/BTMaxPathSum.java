package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

public class BTMaxPathSum {

    private int maxSum = Integer.MIN_VALUE;

    // https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
    public int maxPathSum(TreeNode root, int sum) {
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
