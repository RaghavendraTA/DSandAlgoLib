package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

import java.util.HashMap;

public class BinaryTeeFromPreAndInorder {

    private int preIndex = 0;

    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeUtil(preorder, 0, inorder.length-1, inMap);
    }

    private TreeNode buildTreeUtil(int[] preorder, int start, int end, HashMap<Integer, Integer> inMap) {
        if (start > end) return null;

        int rootVal = preorder[preIndex++];
        int idx = inMap.get(rootVal);

        TreeNode temp = new TreeNode(rootVal);
        temp.left = buildTreeUtil(preorder, start, idx-1, inMap);
        temp.right = buildTreeUtil(preorder, idx+1, end, inMap);
        return temp;
    }
}
