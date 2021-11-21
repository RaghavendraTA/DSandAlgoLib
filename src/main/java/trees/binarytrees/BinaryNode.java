package trees.binarytrees;

public class BinaryNode<T> {

    public T value;
    public int balanceFactor = 0;
    public BinaryNode<T> left, right;

    public BinaryNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public T getValue() { return this.value; }

    public void setValue(T value) { this.value = value; }

    public void setLeft(BinaryNode<T> node) { this.left = node; }

    public void setRight(BinaryNode<T> node) { this.right = node; }
}
