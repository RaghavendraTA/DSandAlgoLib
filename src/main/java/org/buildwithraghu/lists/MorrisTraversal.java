package org.buildwithraghu.lists;

import java.util.*;

public class MorrisTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // --------- InOrder Using Morris Traversal ---------------
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        TreeNode predecessor, current = root;

        while(current != null) {
            if (current.left == null) {
                ans.add(current.val);
                current = current.right;
            } else {
                predecessor = findPredecessor(current);

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    ans.add(current.val);
                    current = current.right;
                }
            }
        }
        return ans;
    }

    private TreeNode findPredecessor(TreeNode node) {
        TreeNode temp = node.left;
        while(temp.right != null && temp.right != node) {
            temp = temp.right;
        }
        return temp;
    }

    // Preorder
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor
                TreeNode predecessor = findPredecessor(current);

                if (predecessor.right == null) {
                    // Make threaded link
                    result.add(current.val);  // preorder: visit BEFORE going left
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Thread already exists — undo it
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }

        return result;
    }

}
