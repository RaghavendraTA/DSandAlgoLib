package trees.binarytrees;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import arrays.cache.Node;

public class BuildTree {

    public static int i = 0;

    public Node usingPreorder(String A) {

        Node newNode = new Node(A.charAt(i));

        if (A.charAt(i) == 'L')
            return newNode;

        i++;
        newNode.left = usingPreorder(A);
        i++;
        newNode.right = usingPreorder(A);

        return newNode;
    }

    public static Node usingInorderAndPreorder() {
        return null;
    }
}
