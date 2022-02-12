package trees.balancingtree;

import arrays.cache.Node;

public class BuildTree {

    int preIndex = 0;

    public Node buildTree(int in[], int pre[], int is, int ie) {
        if (is > ie)
            return null;

        Node root = new Node(pre[preIndex++]);
        int index = 0;
        for (int i = is; i <= ie; i++) {
            if (in[i] == root.value) {
                index = i;
                break;
            }
        }
        root.left = buildTree(in, pre, is, index - 1);
        root.right = buildTree(in, pre, index + 1, ie);

        return null;
    }

    Node buildTree(int in[], int pre[], int n) {
        // Code here
        return buildTree(in, pre, 0, n - 1);

    }
}
