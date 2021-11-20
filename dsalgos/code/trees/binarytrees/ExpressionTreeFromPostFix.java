package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import java.util.Stack;

public class ExpressionTreeFromPostFix {

    public static BinaryNode<Character> buildTree(char[] postfix) {
        Stack<BinaryNode<Character>> stack = new Stack<>();

        for(char c : postfix) {
            if (Character.isLetter(c)) {
                stack.push(new BinaryNode<>(c));
            }
            else {
                BinaryNode<Character> t2 = stack.pop(), t1 = stack.pop();
                BinaryNode<Character> newNode = new BinaryNode<>(c);
                newNode.left = t1;
                newNode.right = t2;
                stack.push(newNode);
            }
        }

        return stack.pop();
    }

    public static void inOrder(BinaryNode<Character> root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.value + ", ");
        inOrder(root.right);
    }

    public static void main(String[] args) {
        BinaryNode<Character> node = buildTree("ABC*+D/".toCharArray());
        inOrder(node);
    }
}
