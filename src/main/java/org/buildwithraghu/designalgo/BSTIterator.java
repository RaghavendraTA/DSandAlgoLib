package org.buildwithraghu.designalgo;

import org.buildwithraghu.utils.TreeNode;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BSTIterator {

    private final Deque<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new ConcurrentLinkedDeque<>();
        this._leftmostInorder(root);
    }

    private void _leftmostInorder(TreeNode root) {
        while (root != null) {
            this.stack.offer(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode topmostNode = this.stack.pollLast();
        if (topmostNode.right != null) {
            this._leftmostInorder(topmostNode.right);
        }
        return topmostNode.val;
    }

    public boolean hasNext() {
        return !this.stack.isEmpty();
    }
}
