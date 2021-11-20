package trees.balancingtree;

import trees.binarytrees.BinaryNode;
import trees.binarytrees.BinarySearchTree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    @Override
    public void insert(T value) {
        this.root = insert(value, this.root);
    }

    @Override
    protected BinaryNode<T> insert(T value, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<>(value);
        }
        if (value.compareTo(node.value) <= 0) {
            node.balanceFactor -= 1;
            node.left = insert(value, node.left);
            node.left = balanceTree(node);
        } else {
            node.balanceFactor += 1;
            node.right = insert(value, node.right);
            node.right = balanceTree(node);
        }
        reCalculateBalanceFactor(node);
        return node;
    }

    private BinaryNode<T> balanceTree(BinaryNode<T> node) {
        if (node.balanceFactor == 2) {
            return (node.right.balanceFactor == 1) ? leftRotate(node) : rightLeftRotate(node);
        } else if (node.balanceFactor == -2) {
            return (node.left.balanceFactor == -1) ? rightRotate(node) : leftRightRotate(node);
        }
        return node;
    }

    private BinaryNode<T> leftRotate(BinaryNode<T> node) {
        BinaryNode<T> returnNode = node.right;
        returnNode.left = node;
        node.right = null;
        return returnNode;
    }

    private BinaryNode<T> rightRotate(BinaryNode<T> node) {
        BinaryNode<T> returnNode = node.left;
        returnNode.right = node;
        node.left = null;
        return returnNode;
    }

    private BinaryNode<T> leftRightRotate(BinaryNode<T> node) {
        BinaryNode<T> returningNode = node.left;
        BinaryNode<T> rNode = returningNode.right;
        rNode.left = returningNode;
        returningNode.right = null;
        node.left = rNode;
        return rightRotate(node);
    }

    private BinaryNode<T> rightLeftRotate(BinaryNode<T> node) {
        BinaryNode<T> returningNode = node.right;
        BinaryNode<T> lNode = returningNode.left;
        lNode.right = returningNode;
        returningNode.left = null;
        node.right = lNode;
        return leftRotate(node);
    }

    private void reCalculateBalanceFactor(BinaryNode<T> node) {
        if (node == null) {
            return;
        } else if (node.left == null && node.right == null) {
            node.balanceFactor = 0;
        } else if (node.left == null) {
            node.balanceFactor = (Math.abs(node.right.balanceFactor) + 1);
        } else if (node.right == null) {
            node.balanceFactor = (Math.abs(node.left.balanceFactor) + 1) * -1;
        }
    }

}
