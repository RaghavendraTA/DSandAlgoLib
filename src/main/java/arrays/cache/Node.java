package arrays.cache;

/*
 * created by raghavendra.ta on 10-Jul-2021
 */

public class Node {

    public int key;
    public int value;
    public Node prev;
    public Node next;
    public Node left;
    public Node right;

    public Node (int key, int value) {
        this.value = value;
        this.key = key;
        this.prev = null;
        this.next = null;
        this.left = null;
        this.right = null;
    }

    public Node(int value) {
        this.value = value;
        this.prev = null;
        this.next = null;
        this.left = null;
        this.right = null;
    }
}