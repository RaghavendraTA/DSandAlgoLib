package org.buildwithraghu.tree;

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

    static class Node<T> {
        T val;
        Node<T> left = null;
        Node<T> right = null;

        public Node(T val) {
            this.val = val;
        }
    }

    private Node<T> root = null;

    @SafeVarargs
    public BinarySearchTree(T...values) {
        for(T v: values)
            this.insert(v);
    }

    private Node<T> _insert(Node<T> node, Node<T> E) {
        if (node == null)
            return E;
        if (E.val.compareTo(node.val) <= 0) {
            node.left = _insert(node.left, E);
        } else {
            node.right = _insert(node.right, E);
        }
        return node;
    }

    private boolean _search(Node<T> node, T E) {
        if (node == null) {
            return false;
        }
        if (E.compareTo(node.val) == 0) {
            return true;
        } else if (node.left != null && E.compareTo(node.val) < 0) {
            return _search(node.left, E);
        } else if (node.right != null) {
            return _search(node.right, E);
        }
        return false;
    }

    private Node<T> getSuccessor(Node<T> curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private Node<T> _delete(Node<T> node, T E) {
        if (node == null) return node;
        if (E.compareTo(node.val) < 0) {
            node.left = _delete(node.left, E);
        } else if (E.compareTo(node.val) > 0) {
            node.right = _delete(node.right, E);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node<T> succ = getSuccessor(node);
            node.val = succ.val;
            node.right = _delete(node.right, succ.val);
        }
        return node;
    }

    private void _levelOrder(Node<T> node, int c, ArrayList<ArrayList<T>> ls) {
        if (node == null) return;
        if (ls.size() <= c) {
            ls.add(new ArrayList<>());
        }
        ls.get(c).add(node.val);
        _levelOrder(node.left, c+1, ls);
        _levelOrder(node.right,c+1, ls);
    }

    public void insert(T E) {
        root = _insert(root, new Node<>(E));
    }

    public boolean search(T E) {
        return _search(root, E);
    }

    public void delete(T E) {
        root = _delete(root, E);
    }

    public String levelOrder() {
        ArrayList<ArrayList<T>> ls = new ArrayList<>();
        _levelOrder(root, 0, ls);
        StringBuilder ans = new StringBuilder();
        for (ArrayList<T> t : ls) {
            ans.append(t.toString());
            ans.append("\n");
        }
        return ans.toString();
    }

    @Override
    public String toString() {
        ArrayList<T> ans = new ArrayList<>();
        Stack<Node<T>> stk = new Stack<>();
        Node<T> node = root;
        while(node != null || !stk.isEmpty()) {
            while(node != null) {
                stk.push(node);
                node = node.left;
            }
            node = stk.pop();
            ans.add(node.val);
            node = node.right;
        }
        return ans.toString();
    }
}
