package org.buildwithraghu.lists;

import org.buildwithraghu.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedList {

    // https://leetcode.com/problems/merge-k-sorted-lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        for(ListNode node: lists) {
            if (node != null)
                pq.add(node);
        }

        ListNode root = new ListNode(-1);
        ListNode tempNode = root;
        while (!pq.isEmpty()) {
            tempNode.next = pq.poll();
            tempNode = tempNode.next;
            if (tempNode.next != null) {
                pq.add(tempNode.next);
            }
        }

        tempNode.next = null;
        return root.next;
    }

    // We can also solve it using counting extra space max(N)
    // and reconstruct the whole list extra space so i.e. space = O(2 * max(N))
    public ListNode mergeKLists_Counting(ListNode[] lists) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(ListNode node: lists) {
            ListNode tempNode = node;
            while(tempNode != null) {
                max = Math.max(max, tempNode.val);
                min = Math.min(min, tempNode.val);
                tempNode = tempNode.next;
            }
        }

        min = Math.abs(min);
        int maxLen = min + max + 1;
        int[] space = new int[maxLen];

        for(ListNode node: lists) {
            ListNode tempNode = node;
            while(tempNode != null) {
                space[tempNode.val + min]++;
                tempNode = tempNode.next;
            }
        }

        ListNode root = new ListNode(-1);
        ListNode curNode = root;

        for(int i = 0; i < space.length; i++) {
            while (space[i] > 0) {
                curNode.next = new ListNode(i - min);
                curNode = curNode.next;
                space[i]--;
            }
        }

        return root.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[2];
        lists[0] = new ListNode(1, new ListNode(3));
        lists[1] = new ListNode(2, new ListNode(4));

        MergeKSortedList ml = new MergeKSortedList();
        ListNode ln = ml.mergeKLists(lists);
        while(ln != null) {
            System.out.print(ln.val + " -> ");
            ln = ln.next;
        }
    }
}
