package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Complexity O(n) and space n + n = 2n => O(2n)
 *
 * I can use leftToRight flag and swap the flag after each iteration,
 * so I can decide which direction traverse again.
 *
 *
 */
public class ZigZagTraversal {

    public static void zigZagTraverse(BinaryNode<Integer> root) {
        if (root == null)
            return;

        BinaryNode<Integer> node;
        boolean leftToRight = true;

        Stack<BinaryNode<Integer>> current = new Stack<>();
        Stack<BinaryNode<Integer>> next = new Stack<>();

        current.push(root);

        while(!current.isEmpty()) {

            node = current.pop();

            if (node != null) {
                System.out.println(node.value);
                if (leftToRight) {
                    if (node.left != null) next.push(node.left);
                    if (node.right != null) next.push(node.right);
                } else {
                    if (node.right != null) next.push(node.right);
                    if (node.left != null) next.push(node.left);
                }
            }

            if (current.isEmpty()) {
                leftToRight = !leftToRight;

                Stack<BinaryNode<Integer>> swapContainer = current;
                current = next;
                next = swapContainer;
            }
        }
    }

    public static Map<Integer, Integer> map = new HashMap<>();

    public static void verticalSum(BinaryNode<Integer> root, int column) {
        if (root == null)
            return;
        verticalSum(root.left, column - 1);
        map.put(column, map.getOrDefault(column, 0) + root.value);
        verticalSum(root.right, column + 1);
    }

    public static void main(String[] args) {
        BinaryNode<Integer> root = new BinaryNode<>(1);
        verticalSum(root, 0);
    }
}
