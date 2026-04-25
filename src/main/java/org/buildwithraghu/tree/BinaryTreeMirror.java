package trees.binarytrees;

import arrays.cache.Node;

public class BinaryTreeMirror {

    public static Node convertToItsMirror(Node root) {
        if (root != null) {
            convertToItsMirror(root.left);
            convertToItsMirror(root.right);
            // swap
            Node temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        return root;
    }

    public static boolean areTheyMirror(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.value != root2.value) {
            return false;
        }
        return areTheyMirror(root1.left, root2.right) && areTheyMirror(root1.right, root2.left);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node2.left = new Node(4);
        node2.right = new Node(5);
        node3.left = new Node(6);
        node3.right = new Node(7);
        root.left = node2;
        root.right = node3;

        Node root2 = new Node(1);
        Node nodet2 = new Node(2);
        Node nodet3 = new Node(3);
        nodet2.left = new Node(4);
        nodet2.right = new Node(5);
        nodet3.left = new Node(6);
        nodet3.right = new Node(7);
        root2.left = nodet2;
        root2.right = nodet3;

        root2 = convertToItsMirror(root2);

        System.out.println(areTheyMirror(root, root2));
    }
}
