package trees.binarytrees;

/*
 * created by raghavendra.ta on 25-Jun-2021
 */

public class NthNodeInBST {

    public static int counter = 0;

    public static void nthLargestNode(int n, BinaryNode<Integer> node) {
        if (node == null) {
            return;
        }
        nthLargestNode(n, node.right);
        counter++;
        if (counter == n) {
            System.out.println(node.getValue());
        }
        nthLargestNode(n, node.left);
    }

    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insertAnArray(5, 3);
        nthLargestNode(2, tree.getRoot());
    }
}
