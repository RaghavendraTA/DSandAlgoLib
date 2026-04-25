package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

public class HeightOfTree {

    private int maxHeight = 0;

    public int heightOfTree(TreeNode root, int curHeight) {
        if (root == null) return curHeight;
        maxHeight = Math.max(maxHeight, heightOfTree(root.left, curHeight+1));
        maxHeight = Math.max(maxHeight, heightOfTree(root.right, curHeight+1));
        return maxHeight;
    }

    public int maxDepth(TreeNode root) {
        heightOfTree(root, 0);
        return maxHeight;
    }
}
