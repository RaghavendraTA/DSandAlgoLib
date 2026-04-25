package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

import java.util.List;

public class TreePathSum {

    // https://leetcode.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null || targetSum < 0) {
            return false;
        }
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null)
            return true;

        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    // https://leetcode.com/problems/path-sum-ii/description/
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return null;
    }
}
