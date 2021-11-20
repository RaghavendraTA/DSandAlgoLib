package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Traversal {

    public static BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    public static void preOrder(BinaryNode<Integer> root) {
        if (root == null) return;
        System.out.print(root.value + ", ");
        inOrder(root.left);
        inOrder(root.right);
    }

    public static void nonRecursivePreOrder(BinaryNode<Integer> root) {
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        while(true) {
            while(root != null) {
                System.out.print(root.value + ", ");
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            root = stack.pop();
            root = root.right;
        }
    }

    public static void inOrder(BinaryNode<Integer> root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.value + ", ");
        inOrder(root.right);
    }

    public static void nonRecursiveInOrder(BinaryNode<Integer> root) {
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty())
                break;
            root = stack.pop();
            System.out.println(root.value);
            root = root.right;
        }
    }

    public static void postOrder(BinaryNode<Integer> root) {
        if (root == null) return;
        inOrder(root.left);
        inOrder(root.right);
        System.out.print(root.value + ", ");
    }

    public static void nonRecursivePostOrder(BinaryNode<Integer> root) {
        Stack<BinaryNode<Integer>> stack = new Stack<>();
        BinaryNode<Integer> prev = null;
        do {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            while(root == null && !stack.isEmpty()) {
                root = stack.peek();
                if (root.right == null || root.right == prev) {
                    System.out.println(root.value + ", ");
                    stack.pop();
                    prev = root;
                    root = null;
                } else {
                    root = root.right;
                }
            }
        } while(!stack.isEmpty());
    }

    public static void levelOrder(BinaryNode<Integer> root) {
        BinaryNode<Integer> temp;
        Deque<BinaryNode<Integer>> queue = new LinkedList<>();

        if (root == null)
            return;

        queue.addLast(root);

        while(!queue.isEmpty()) {
            temp = queue.pollFirst();
            System.out.println(temp.value + ", ");
            if (temp.left != null)
                queue.addLast(temp.left);
            if (temp.right != null)
                queue.addLast(temp.right);
        }
    }

}
