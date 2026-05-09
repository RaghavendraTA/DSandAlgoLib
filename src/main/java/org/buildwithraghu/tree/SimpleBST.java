package org.buildwithraghu.tree;

public class SimpleBST {

    static class BNode {
        int value;
        BNode left, right;

        BNode(int value) {
            this.value = value;
        }
    }

    private BNode root = null;

    public void insert(int val) {
        root = insert(root, val);
    }

    public boolean search(int val) {
        return search(root, val);
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    // ---------- Internal ----------

    private BNode insert(BNode node, int val) {
        if (node == null) return new BNode(val);

        if (val < node.value)
            node.left = insert(node.left, val);
        else if (val > node.value)
            node.right = insert(node.right, val);
        // ignore duplicates

        return node;
    }

    private boolean search(BNode node, int val) {
        if (node == null) return false;

        if (val == node.value) return true;
        if (val < node.value) return search(node.left, val);
        return search(node.right, val);
    }

    private BNode delete(BNode node, int val) {
        if (node == null) return null;

        if (val < node.value) {
            node.left = delete(node.left, val);
        } else if (val > node.value) {
            node.right = delete(node.right, val);
        } else {
            // 1 child or 0 child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // 2 children
            BNode successor = findMin(node.right);
            node.value = successor.value;
            node.right = delete(node.right, successor.value);
        }

        return node;
    }

    private BNode findMin(BNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
