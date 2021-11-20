package trees.binarytrees;

import trees.utils.Pair;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public void insert(T value) {
        root = insert(value, root);
    }

    @SafeVarargs
    public final void insertAnArray(T... value) {
        Arrays.stream(value).forEach(this::insert);
    }

    protected BinaryNode<T> insert(T value, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<>(value);
        }
        if (value.compareTo(node.value) <= 0) {
            node.left = insert(value, node.left);
        } else {
            node.right = insert(value, node.right);
        }
        return node;
    }

    protected BinaryNode<T> delete(T value, BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.value) == 0) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null){
                return node.left;
            }
        }
        if (value.compareTo(node.value) < 0) {
            node.left = delete(value, node.left);
        } else if (value.compareTo(node.value) > 0) {
            node.right = delete(value, node.right);
        }
        return node;
    }

    public void delete(T value) {
        this.root = this.delete(value, this.root);
    }

    private Stack<T> find(T value, BinaryNode<T> node, Stack<T> traversed) {
        if (node == null) {
            return new Stack<>();
        }
        traversed.push(node.value);
        if (value.compareTo(node.value) == 0) {
            return traversed;
        } else if (value.compareTo(node.value) < 0) {
            return find(value, node.left, traversed);
        } else {
            return find(value, node.right, traversed);
        }
    }

    public Stack<T> find(T value) {
        return find(value, this.root, new Stack<>());
    }

    private Queue<Pair<T, Integer>> readZigZag(BinaryNode<T> node) {
        Queue<Pair<T, Integer>> result = new LinkedList<>();
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(node);

        BinaryNode<T> temp;
        while(!queue.isEmpty()) {
            temp = queue.poll();
            result.add(new Pair<>(temp.value, temp.balanceFactor));
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        return result;
    }

    public String printNodesInZigZag() {
        Queue<Pair<T, Integer>> q = readZigZag(this.root);
        List<String> ls = new ArrayList<>();
        while(!q.isEmpty()) {
            Pair<T, Integer> p = q.poll();
            ls.add(p.getKey() + "(bf=" + p.getValue() + ") -> ");
        }
        return String.join(" -> ", ls);
    }

    public BinaryNode<T> getRoot() {
        return this.root;
    }
}
