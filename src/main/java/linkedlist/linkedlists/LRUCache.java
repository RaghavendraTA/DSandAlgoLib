package linkedlist.linkedlists;

/*
 * created by raghavendra.ta on 05-Jul-2021
 */

import arrays.cache.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * {
 *     "key":  [value],         <- tail
 *                ||
 *     "key2": [value2],
 *                ||
 *     "key3": [value4],        <- head
 * }
 *
 * over get method remove at a position and put it in front of the head
 *
 * space O(n), Complexity O(1) = get and put
 */

public class LRUCache {

    private int size, capacity;
    private Node head = null, tail = null;
    Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    private Node insert(int key, int value) {
        Node newNode = new Node(key, value);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else if (head != null){
            head.next = newNode;
            newNode.prev = head;
            head = newNode;
        }
        size++;
        return head;
    }

    private int remove(Node node) {
        int value = node.value;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            tail = tail.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            head = head.prev;
        }
        size--;
        return value;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = remove(map.get(key));
            map.put(key, insert(key, value));
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        if (size >= capacity && tail != null) {
            map.remove(tail.key);
            remove(tail);
        }
        map.put(key, insert(key, value));
    }

    public void printDLL() {
        System.out.print("List = ");
        Node temp = tail;
        while(temp != null) {
            System.out.print(temp.value + ", ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.printDLL();
        cache.put(1, 2);
        cache.printDLL();
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

}