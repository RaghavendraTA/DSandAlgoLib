package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

public class KthSmallestInBST {

    private TreeNode ans = null;
    private int count = 0;

    private void inorder(TreeNode root, int k) {
        if (ans != null || root == null) return;
        inorder(root.left, k);
        count++;
        if (count == k) ans = root;
        inorder(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return ans.val;
    }
}
