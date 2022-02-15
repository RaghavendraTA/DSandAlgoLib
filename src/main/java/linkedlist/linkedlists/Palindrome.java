package linkedlist.linkedlists;

import arrays.cache.Node;

import java.util.Stack;

public class Palindrome {

    // Function when LL node is integer
    boolean isPalindrome(Node head)
    {
        int len = 0;
        Node temp = head;
        while(temp != null) {
            len++;
            temp = temp.next;
        }
        int diff = len / 2;
        int sum = 0;
        temp = head;
        while(diff > 0) {
            sum += temp.value;
            temp = temp.next;
            diff--;
        }
        if (len % 2 != 0)
            temp = temp.next;
        while(temp != null) {
            sum -= temp.value;
            temp = temp.next;
        }
        return sum == 0;
    }

    // Using Stack for non-integer nodes
    boolean isPalindromeForStringNode(Node head) {
        Stack<Integer> stack = new Stack<>();
        int len = 0;
        Node temp = head;
        while(temp != null) {
            len++;
            temp = temp.next;
        }
        int n = len / 2;
        temp = head;
        for(int i = 0; i < n; i++) {
            stack.push(temp.value);
            temp = temp.next;
        }
        if (len % 2 != 0)
            temp = temp.next;
        while(temp != null && !stack.isEmpty()) {
            if (stack.pop() != temp.value) return false;
            temp = temp.next;
        }
        return stack.isEmpty();
    }
}
