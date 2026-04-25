package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

public class InvertBinaryTree {

    // https://leetcode.com/problems/invert-binary-tree/description/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
