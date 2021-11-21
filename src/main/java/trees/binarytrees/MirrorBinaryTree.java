package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

public class MirrorBinaryTree {

    public static BinaryNode<Integer> mirrorBinaryTree(BinaryNode<Integer> root) {
        BinaryNode<Integer> temp;
        if (root != null) {
            mirrorBinaryTree(root.left);
            mirrorBinaryTree(root.right);
            temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        return root;
    }

}
