package org.buildwithraghu.tree;

import org.buildwithraghu.utils.TreeNode;

import java.util.*;

public class BinaryTreeRightSideView {

    // https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> que = new ArrayDeque<>();
        que.offer(root);

        while(!que.isEmpty()) {
            int levelSize = que.size();
            for(int i = 0; i < levelSize; i++) {
                TreeNode node = que.poll();
                assert node != null;
                if (node.left != null)
                    que.offer(node.left);
                if (node.right != null)
                    que.offer(node.right);
                if (i == levelSize - 1)
                    ans.add(node.val);
            }
        }

        return ans;
    }
}
