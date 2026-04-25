package org.buildwithraghu.linkedin;

import org.buildwithraghu.utils.TreeNode;

public class LowestCommonAncestorOfABTII_1644 {

    boolean foundP, foundQ;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = lca(root, p, q);
        if (foundP && foundQ) return res;
        else return null;
    }

    TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        TreeNode l = lca(root.left, p, q);
        TreeNode r = lca(root.right, p, q);

        if (root == p) {
            foundP = true;
            return p;
        }
        if (root == q) {
            foundQ = true;
            return q;
        }

        if (l == null) return r;
        if (r == null) return l;

        return root;
    }
}
