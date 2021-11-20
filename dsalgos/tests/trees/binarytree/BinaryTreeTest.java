package trees.binarytree;

import org.junit.jupiter.api.Test;
import trees.binarytrees.BinarySearchTree;
import trees.binarytrees.BinaryTree;

public class BinaryTreeTest {

    @Test
    public void testBinaryTreeZigZagConstruction() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insertInZigzagOrder(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        tree.inorder();
    }

    @Test
    public void testBinarySearchTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insertAnArray(10, 5, 15, 3, 7, 13, 17, 1, 2, 6, 8, 11, 12, 16);
        tree.delete(17);
        System.out.println(tree.find(1));
        tree.inorder();
    }
}
