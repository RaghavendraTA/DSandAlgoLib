package linkedlist.linkedlists;

import arrays.cache.Node;

import java.util.Stack;

public class AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() { }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // Linked Lists are reversed
    // Example: 123 + 117 = 240
    // Input will be given l1 => 3 -> 2 -> 1 and l2 => 7 -> 1 -> 1
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode root = null, tail = null;
        while(l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            ListNode temp = new ListNode(sum % 10);
            if (root == null) {
                root = temp;
                tail = temp;
            } else {
                tail.next = temp;
                tail = tail.next;
            }
            l1 = l1.next;
            l2 = l2.next;
            carry = sum >= 10 ? 1 : 0;
        }
        while(l1 != null) {
            int sum = l1.val + carry;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            l1 = l1.next;
            carry = sum / 10;
        }
        while(l2 != null) {
            int sum = l2.val + carry;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            l2 = l2.next;
            carry = sum / 10;
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return root;
    }

    public static int len(Node node) {
        int count = 0;
        while(node != null) {
            node = node.next;
            count++;
        }
        return count;
    }

    // Add two numbers when linked list are straight
    // Example: 45 + 345 = 390
    // Input will be given l1 => 4 -> 5 and l2 => 3 -> 4 -> 5
    public static Node addTwoNumbers(Node l1, Node l2) {
        int l1Len = len(l1);
        int l2Len = len(l2);
        int diff = Math.abs(l1Len - l2Len);
        if (l2Len > l1Len) {
            Node nTemp = l1;
            l1 = l2;
            l2 = nTemp;
        }
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        while(cnt < diff && l1.next != null) {
            stack.push(l1.value);
            l1 = l1.next;
            cnt++;
        }
        while(l1 != null) {
            stack.push(l1.value + l2.value);
            l1 = l1.next;
            l2 = l2.next;
        }
        Node result = null;
        int carry = 0;
        while(!stack.isEmpty()) {
            int t = carry + stack.pop();
            Node temp = new Node(t % 10);
            carry = t >= 10 ? 1 : 0;
            temp.next = result;
            result = temp;
        }
        if (carry > 0) {
            Node temp = new Node(carry);
            temp.next = result;
            result = temp;
        }
        return result;
    }

    // Alternative solution when linkedList is straight using two stacks
    static Node addTwoLists(Node l1, Node l2) {
        Stack<Node> s1 = new Stack<>(), s2 = new Stack<>();
        Node root = null;
        while(l1 != null) {
            s1.push(l1);
            l1 = l1.next;
        }
        while(l2 != null) {
            s2.push(l2);
            l2 = l2.next;
        }
        int carry = 0;
        while(!s1.isEmpty() && !s2.isEmpty()) {
            int ans = s1.pop().value + s2.pop().value + carry;
            Node n = new Node(ans % 10);
            carry = ans / 10;
            n.next = root;
            root = n;
        }
        while(!s1.isEmpty()) {
            int ans = s1.pop().value + carry;
            Node n = new Node(ans % 10);
            carry = ans / 10;
            n.next = root;
            root = n;
        }
        while(!s2.isEmpty()) {
            int ans = s2.pop().value + carry;
            Node n = new Node(ans % 10);
            carry = ans / 10;
            n.next = root;
            root = n;
        }
        if (carry > 0) {
            Node n = new Node(carry);
            n.next = root;
            root = n;
        }
        return root;
    }

    public static void main(String[] args) {
        int[] l1 = new int[]{7, 8, 3, 6, 9, 4};
        int[] l2 = new int[]{4, 8, 5, 7, 2, 3, 8, 3};

        Node temp;
        Node l1List = new Node(1);
        temp = l1List;
        for(int i: l1) {
            temp.next = new Node(i);
            temp = temp.next;
        }

        Node l2List = new Node(8);
        temp = l2List;
        for(int i: l2) {
            temp.next = new Node(i);
            temp = temp.next;
        }

        Node result = addTwoNumbers(l1List, l2List);
        while(result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}
