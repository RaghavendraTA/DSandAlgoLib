package trees.binarytrees;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

import arrays.cache.Node;

import java.util.regex.Pattern;

public class BuildTreeUsingPreorder {

    public static int i = 0;

    public Node buildTree(String A) {

        Node newNode = new Node(A.charAt(i));

        if (A.charAt(i) == 'L')
            return newNode;

        i++;
        newNode.left = buildTree(A);
        i++;
        newNode.right = buildTree(A);

        return newNode;
    }
}
