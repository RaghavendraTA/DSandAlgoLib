package trees.binarytrees;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import arrays.cache.Node;

public class TreeUsingInorderAndPreorder {

    public static int preorderIndex = 0;

    public static Node buildTree(int[] inorder, int[] preorder,
                                 int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd)
            return null;

        Node newNode = new Node(preorder[preorderIndex]);
        preorderIndex++;

        if (inorderStart == inorderEnd)
            return newNode;

        int inorderIndex = search(inorder, inorderStart, inorderEnd, newNode.value);

        newNode.left = buildTree(inorder, preorder, inorderStart, inorderIndex - 1);
        newNode.right = buildTree(inorder, preorder,  inorderIndex + 1, inorderEnd);

        return newNode;
    }

    private static int search(int[] inorder, int inorderStart,
                              int inorderEnd, int value) {

        for(int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == value)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        preorderIndex = 0;
        int[] inorder = new int[]{-1};
        int[] preorder = new int[]{-1};
        buildTree(inorder, preorder, 0, inorder.length-1);
    }
}
