package arrays.stack;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import trees.binarytrees.BinaryNode;

public class EvaluateExpressionTree {

    static int evaluate(BinaryNode<Character> root) {

        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return root.value - '0';

        int left_sum = evaluate(root.left);

        int right_sum = evaluate(root.right);

        if (root.value == '+')
            return left_sum + right_sum;

        else if (root.value == '-')
            return (left_sum - right_sum);

        else if (root.value == '*')
            return (left_sum * right_sum);

        else
            return (left_sum / right_sum);
    }
}
