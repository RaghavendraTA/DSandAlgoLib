package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

public class DiameterOfTree {
    private int maxLen = 0;

    // Returns height of subtree rooted at 'root'
    private int getHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Diameter at this node = leftHeight + rightHeight
        maxLen = Math.max(maxLen, leftHeight + rightHeight);

        // Return height of this subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        getHeight(root);
        return maxLen;
    }
}
