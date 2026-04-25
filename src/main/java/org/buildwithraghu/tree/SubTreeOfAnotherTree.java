package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

// https://leetcode.com/problems/subtree-of-another-tree/
public class SubTreeOfAnotherTree {
    // Using Recursion
    public boolean isSub(TreeNode root, TreeNode sub) {
        if(root == null && sub == null) return true;
        if(root == null || sub == null) return false;
        if(root.val != sub.val) return false;
        return isSub(root.left, sub.left) && isSub(root.right, sub.right);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) return false;
        if(isSub(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}
