package org.buildwithraghu.linkedin;

import org.buildwithraghu.utils.TreeNode;

import java.util.Stack;

public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public class Node {
        int val;
        Node left;
        Node right;
        Node(int x) { val = x; }
        @Override
        public String toString() {
            return val + ":{"
                    + (left == null ? "null" : left.toString())
                    + ","
                    + (right == null ? "null" : right.toString())
                    + "}";
        }
        Node(TreeNode tnode) {
            this.val = tnode.val;
            this.left = tnode.left == null ? null : new Node(tnode.left);
            this.right = tnode.right == null ? null : new Node(tnode.right);
        }
    }

    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        return new Node(root).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("null"))
            return null;
        String val = data.substring(0, data.indexOf(":"));
        TreeNode node = new TreeNode(Integer.parseInt(val));
        String remaining = data.substring(data.indexOf(":") + 1);
        String[] pair = findSplitIndex(remaining);
        node.left = deserialize(pair[0]);
        node.right = deserialize(pair[1]);
        return node;
    }

    private String[] findSplitIndex(String data) {
        data = data.substring(1, data.length()-1);
        if (data.startsWith("null")) {
            String right = data.substring(5);
            return new String[]{null, right.startsWith("null") ? null : right};
        }
        Stack<Character> stk = new Stack<>();
        boolean flag = false;
        for(int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == '{') {
                stk.push('{');
                flag = true;
            } if (c == '}')
                stk.pop();
            if (stk.isEmpty() && flag) {
                return new String[]{data.substring(0, i + 1), data.substring(i + 2)};
            }
        }
        return new String[]{null, null};
    }

    static void main() {
        TreeNode llnode = new TreeNode(5);
        TreeNode rlnode = new TreeNode(6);
        TreeNode lnode = new TreeNode(2);
        lnode.left = llnode;
        lnode.right = rlnode;

        TreeNode lrnode = new TreeNode(7);
        TreeNode rrnode = new TreeNode(8);
        TreeNode rnode = new TreeNode(3);
        rnode.left = lrnode;
        rnode.right = rrnode;

        TreeNode node = new TreeNode(1);
        node.left = lnode;
        node.right = rnode;

        SerializeAndDeserializeBinaryTree s = new SerializeAndDeserializeBinaryTree();
        String serialized = s.serialize(node);
        //System.out.println(serialized);
        TreeNode tnode = s.deserialize(serialized);
    }
}
