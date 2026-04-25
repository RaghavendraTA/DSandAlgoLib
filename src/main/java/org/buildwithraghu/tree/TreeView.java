package trees.binarytrees;

/*
 * created by raghavendra.ta on 25-Jun-2021
 */

public class TreeView {

    public static int maxLevel = 0;

    private static void printLeftView(int level, BinaryNode<Integer> node) {
        if (node == null) {
            return;
        }
        if (maxLevel < level) {
            System.out.print(node.getValue() + ", ");
            maxLevel = level;
        }
        printLeftView(level + 1, node.left);
        printLeftView(level + 1, node.right);
    }

    public static void printLeftView(BinarySearchTree<Integer> tree) {
        maxLevel = 0;
        printLeftView(1, tree.getRoot());
    }

    private static void printRightView(int level, BinaryNode<Integer> node) {
        if (node == null) {
            return;
        }
        if (maxLevel < level) {
            System.out.print(node.getValue() + ", ");
            maxLevel = level;
        }
        printRightView(level + 1, node.right);
        printRightView(level + 1, node.left);
    }

    public static void printRightView(BinarySearchTree<Integer> tree) {
        maxLevel = 0;
        printRightView(1, tree.getRoot());
    }

    public static boolean isAtRoot = false;

    public static void pyramidView(BinaryNode<Integer> root, BinaryNode<Integer> node) {
        if (node == null)
            return;
        if (!isAtRoot)
            pyramidView(root, node.left);
        if (root == node)
            isAtRoot = true;
        System.out.println(node.getValue());
        if (isAtRoot)
            pyramidView(root, node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insertAnArray(5, 3, 1, 4, 7, 6, 8, 9);
        pyramidView(tree.getRoot(), tree.getRoot());
    }

}
