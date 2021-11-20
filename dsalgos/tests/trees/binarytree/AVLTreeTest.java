package trees.binarytree;

import org.junit.jupiter.api.Test;
import trees.balancingtree.AVLTree;

public class AVLTreeTest {

    @Test
    public void testAVLTreeInsertion() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(12);
        //tree.insert(13);
        //tree.insert(14);
        System.out.println(tree.printNodesInZigZag());
    }
}
