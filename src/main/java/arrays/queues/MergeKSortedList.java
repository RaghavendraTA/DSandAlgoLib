package arrays.queues;

/*
 * created by raghavendra.ta on 11-Jul-2021
 */

import arrays.cache.Node;
import linkedlist.linkedlists.SingleLinkedList;
import linkedlist.node.ListNode;

import java.util.*;

/**
 *
 * Iterate over each list add every node to min-heap
 * poll() resulting data should set the point to next
 *
 * complexity => O(N logN), Space => O(N)
 * where N is the number of nodes
 */
public class MergeKSortedList {

    public static Node mergeKSortedListWithTotalNElements(Node[] lists) {

        if (lists.length == 0)
            return null;

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        for (Node l : lists) {
            Node t = l;
            while (t != null) {
                q.add(t);
                t = t.next;
            }
        }

        Node head = q.poll();
        Node result = head;

        while (q.size() > 0) {
            head.next = q.poll();
            head = head.next;
        }
        if (head != null) {
            head.next = null;
        }

        return result;
    }

    public static void main(String[] args) {

        Node[] listNodeList = new Node[5];
        int k = 0;
        Random random = new Random();

        for(int i = 0; i < 5; i++) {
            Node root = new Node(random.nextInt(100));
            Node head = root;
            for(int j = 1; j < 5; j++) {
                root.next = new Node(random.nextInt(100));
                root = root.next;
            }
            listNodeList[k++] = head;
        }

        Node node = mergeKSortedListWithTotalNElements(listNodeList);

        while(node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

}
