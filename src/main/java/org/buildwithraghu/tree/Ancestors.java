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

    // Works for BinaryTree
    public static Node lowestCommonAncestor(Node root, int alpha, int beta) {
        if (root == null)
            return root;

        if (root.value == alpha || root.value == beta)
            return root;

        Node left = lowestCommonAncestor(root.left, alpha, beta);
        Node right = lowestCommonAncestor(root.right, alpha, beta);

        if (left != null && right != null)
            return root;
        else
            return (left != null ? left : right);
    }

}

