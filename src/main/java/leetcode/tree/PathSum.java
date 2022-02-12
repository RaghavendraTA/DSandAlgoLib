package leetcode.tree;

import java.util.List;

public class PathSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        if (targetSum == 0 && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }

    // Root to leaf path sum
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        return null;
    }
}
