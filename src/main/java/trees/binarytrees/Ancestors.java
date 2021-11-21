package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import arrays.cache.Node;

public class Ancestors {

    public static boolean printAllAncestors(Node root, Node node) {
        if (root == null) {
            return false;
        }

        if (root.left == node || root.right == node || printAllAncestors(root.left, node) ||
                printAllAncestors(root.right, node)) {
            System.out.println(root.value);
            return true;
        }
        return false;
    }

    // Works for BST
    // The Shortest path between 2 nodes is nothing but LCA between 2 nodes
    public static Node lowestCommonAncestorForGiven2Pointer(Node root, Node alpha, Node beta) {
        while (true) {
            if ((alpha.value < root.value && beta.value > root.value) ||
                    (alpha.value > root.value && beta.value < root.value)) {
                return root;
            }
            if (alpha.value < root.value)
                root = root.left;
            else
                root = root.right;
        }
    }

    // Works for BinaryTree
    public static Node lowestCommonAncestor(Node root, Node alpha, Node beta) {
        if (root == null)
            return root;

        if (root == alpha || root == beta)
            return root;

        Node left = lowestCommonAncestor(root.left, alpha, beta);
        Node right = lowestCommonAncestor(root.right, alpha, beta);

        if (left != null && right != null)
            return root;
        else
            return (left != null ? left : right);
    }

}
