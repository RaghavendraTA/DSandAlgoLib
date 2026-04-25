package org.buildwithraghu.tree;

import org.buildwithraghu.utilities.Node;

public class SortedArrayToBinarySearchTree {

    public static Node construct(int[] A, int left, int right) {

        if (left > right)
            return null;

        Node newNode;
        int mid = (left + right) / 2;

        if (left == right) {
            newNode = new Node(A[left]);
        }
        else {
            newNode = new Node(A[mid]);
            newNode.left = construct(A, left, mid - 1);
            newNode.right = construct(A, mid + 1, right);
        }

        return newNode;
    }
}
