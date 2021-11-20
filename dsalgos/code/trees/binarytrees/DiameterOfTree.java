package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import trees.utils.Pair;

public class DiameterOfTree {

    public static int diameter = 0;

    public static int leftNode, rightNode;

    // This solution gives leftNode and rightNode as well.
    public static Pair<Integer, Integer> diameterUsingRecursion(BinaryNode<Integer> root, BinaryNode<Integer> parent) {
        if (root == null)
            return new Pair<>(parent.value, 0);

        Pair<Integer, Integer> left = diameterUsingRecursion(root.left, root);
        Pair<Integer, Integer> right = diameterUsingRecursion(root.right, root);

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
    public static int diameterOfBinaryTree(BinaryNode<Integer> root) {
        diameter = 0;
        diameterUsingRecursion(root, null);
        System.out.println("left = " + leftNode + ", right = " + rightNode);
        return diameter;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insertAnArray(100, 50, 200, 40, 60, 300, 30, 61, 35, 62, 39, 63);
        diameterOfBinaryTree(tree.getRoot());
    }
}
