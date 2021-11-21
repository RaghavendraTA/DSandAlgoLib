package trees.binarytrees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

    protected BinaryNode<T> root = null;

    public void insertInZigzagOrder(T[] arr) {
        if (arr.length == 0) {
            return;
        }
        root = new BinaryNode<>(arr[0]);
        BinaryNode<T> temp = null;
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            temp = queue.poll();
            if (i < arr.length) {
                BinaryNode<T> leftNode = new BinaryNode<>(arr[i++]);
                temp.setLeft(leftNode);
                queue.add(leftNode);
            }
            if (i < arr.length) {
                BinaryNode<T> rightNode = new BinaryNode<>(arr[i++]);
                temp.setRight(rightNode);
                queue.add(rightNode);
            }
        }
    }

    private void inorder(BinaryNode<T> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.getValue() + " -> ");
        inorder(node.right);
    }

    public void inorder() {
        inorder(root);
        System.out.println("\b\b\b");
    }
}
