package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

public class HasPathSum {

    boolean hasPathSum(BinaryNode<Integer> root, int sum) {

        if (root == null) {
            return (sum == 0);
        }

        int remainingSum = sum - root.value;

        if (root.left != null && root.right != null) {
            return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
        }
        else if (root.right != null) {
            return hasPathSum(root.left, remainingSum);
        }
        else {
            return hasPathSum(root.right, remainingSum);
        }

    }
}
