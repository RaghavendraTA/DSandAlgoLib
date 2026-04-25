package trees.binarytrees;

import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<T>> {

    protected BinaryNode<T> root = null;

    public void insert(T value) {
        root = _insert(root, value);
    }

    private BinaryNode<T> _insert(BinaryNode<T> node, T E) {
        if (node == null)
            return new BinaryNode<>(E);
        if (E.compareTo(node.value) <= 0) {
            node.left = _insert(node.left, E);
        } else {
            node.right = _insert(node.right, E);
        }
        return node;
    }

    public boolean search(T E) {
        return _search(root, E);
    }

    private boolean _search(BinaryNode<T> node, T E) {
        if (node == null) {
            return false;
        }
        if (E.compareTo(node.value) == 0) {
            return true;
        } else if (node.left != null && E.compareTo(node.value) < 0) {
            return _search(node.left, E);
        } else if (node.right != null) {
            return _search(node.right, E);
        }
        return false;
    }

    public void delete(T E) {
        root = _delete(root, E);
    }

    private BinaryNode<T> _delete(BinaryNode<T> node, T E) {
        if (node == null) return node;
        if (E.compareTo(node.value) < 0) {
            node.left = _delete(node.left, E);
        } else if (E.compareTo(node.value) > 0) {
            node.right = _delete(node.right, E);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            BinaryNode<T> succ = getSuccessor(node);
            node.value = succ.value;
            node.right = _delete(node.right, succ.value);
        }
        return node;
    }

    private BinaryNode<T> getSuccessor(BinaryNode<T> curr) {
        curr = curr.right;
        while (curr != null && curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public BinaryNode<T> getRoot() {
        return root;
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

    private void _levelOrder(BinaryNode<T> node, int c, ArrayList<ArrayList<T>> ls) {
        if (node == null) return;
        if (ls.size() <= c) {
            ls.add(new ArrayList<>());
        }
        ls.get(c).add(node.value);
        _levelOrder(node.left, c+1, ls);
        _levelOrder(node.right, c+1, ls);
    }

    @Override
    public String toString() {
        ArrayList<T> ans = new ArrayList<>();
        Stack<BinaryNode<T>> stk = new Stack<>();
        BinaryNode<T> node = root;
        while(node != null || !stk.isEmpty()) {
            while(node != null) {
                stk.push(node);
                node = node.left;
            }
            node = stk.pop();
            ans.add(node.value);
            node = node.right;
        }
        return ans.toString();
    }
}
