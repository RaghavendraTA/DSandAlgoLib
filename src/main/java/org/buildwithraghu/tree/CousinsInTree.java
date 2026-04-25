package trees.binarytrees;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import java.util.Deque;
import java.util.LinkedList;

public class CousinsInTree {

    int findCousinSum(BinaryNode<Integer> root, int key) {

        if (root == null) {
            return -1;
        }

        if (root.value == key) {
            return -1;
        }

        int currSum = 0;

        int size;

        Deque<BinaryNode<Integer>> q = new LinkedList<>();
        q.add(root);

        boolean found = false;

        while (q.size() > 0) {
            if (found) {
                return currSum;
            }

            size = q.size();
            currSum = 0;

            while (size > 0) {

                root = q.peek();
                q.remove();

                if ((root.left != null && root.left.value == key)
                        || (root.right != null && root.right.value == key)) {
                    found = true;
                }

                else {
                    if (root.left != null) {
                        currSum += root.left.value;
                        q.add(root.left);
                    }

                    if (root.right != null) {
                        currSum += root.right.value;
                        q.add(root.right);
                    }
                }

                size--;
            }
        }

        return -1;
    }
}
