package trees.binarytrees;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class SubTreeOfAnotherTree {

    boolean isSub(BinaryNode<Integer> root, BinaryNode<Integer> sub) {
        if(root == null && sub == null) return true;

        if(root == null || sub == null) return false;

        if(root.value != sub.value) return false;

        return isSub(root.left, sub.left) && isSub(root.right, sub.right);
    }

    boolean isSubtree(BinaryNode<Integer> root, BinaryNode<Integer> subRoot) {
        if(root == null) return false;

        if(isSub(root, subRoot)) return true;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}
