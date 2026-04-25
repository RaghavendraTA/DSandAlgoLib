package org.buildwithraghu.linkedin;

import org.buildwithraghu.utils.TreeNode;

public class MaxDepthOfBinaryTree_104 {

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
