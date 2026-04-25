package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTraversal {
    private List<Integer> ans = new ArrayList<>();

    // inorder Traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return ans;
        inorderTraversal(root.left);
        ans.add(root.val);
        inorderTraversal(root.right);
        return ans;
    }

    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans2 = new ArrayList<>();
        levelOrderUtil(ans2, root, 0);
        return ans2;
    }

    private void levelOrderUtil(List<List<Integer>> ans2, TreeNode root, int level) {
        if (root == null) return;
        if (ans2.size() < level+1)
            ans2.add(new ArrayList<>());
        ans2.get(level).add(root.val);
        levelOrderUtil(ans2, root.left, level+1);
        levelOrderUtil(ans2, root.right, level+1);
    }
}
