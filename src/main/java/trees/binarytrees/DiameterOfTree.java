package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import arrays.cache.Node;
import trees.utils.Pair;

public class DiameterOfTree {

    public static int diameter = 0;

    public static int leftNode, rightNode;

    // This solution gives leftNode and rightNode as well.
    public static Pair<Integer, Integer> diameterGetNodes(BinaryNode<Integer> root, BinaryNode<Integer> parent) {
        if (root == null)
            return new Pair<>(parent.value, 0);

        Pair<Integer, Integer> left = diameterGetNodes(root.left, root);
        Pair<Integer, Integer> right = diameterGetNodes(root.right, root);

        if (left.getValue() + right.getValue() > diameter) {
            diameter = left.getValue() + right.getValue();
            if (left.getKey() != null)
                leftNode = left.getKey();
            if (right.getKey() != null)
                rightNode = right.getKey();
        }

        Pair<Integer, Integer> returnNode;
        if (left.getValue() > right.getValue()) {
            returnNode = left;
        } else {
            returnNode = right;
        }

        return new Pair<>(returnNode.getKey(), returnNode.getValue() + 1);
    }

    // Starting point
    public static int diameterGetNodes(BinaryNode<Integer> root) {
        diameter = 0;
        diameterGetNodes(root, null);
        System.out.println("left = " + leftNode + ", right = " + rightNode);
        return diameter;
    }

    // Easy solution
    int ans = 0;

    int height(Node root) {

        if (root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        ans = Math.max(ans, 1 + lh + rh);

        return 1 + Math.max(lh, rh);
    }

    int diameter(Node root) {
        height(root);
        return ans;
    }

    // Simplest solution
    public static int diameterAns = 0;

    public static int diameterUsingRecursion(BinaryNode<Integer> root) {
        if (root == null)
            return 0;
        int left = diameterUsingRecursion(root.left);
        int right = diameterUsingRecursion(root.right);
        diameterAns = Math.max(diameterAns, left + right);
        return Math.max(left, right) + 1;
    }

    // Starting point
    public int diameterOfBinaryTree(BinaryNode<Integer> root) {
        diameterAns = 0;
        diameterUsingRecursion(root);
        return diameterAns;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insertAnArray(100, 50, 200, 40, 60, 300, 30, 61, 35, 62, 39, 63);
        diameterGetNodes(tree.getRoot());
    }
}
