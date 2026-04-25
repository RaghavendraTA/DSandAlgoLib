import java.util.*;

class Solution {
    
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = null, temp = null;
		int sum = 0, carry = 0;
        while(l1 != null && l2 != null) {
			sum = l1.val + l2.val + carry;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			if (ans == null) {
				ans = node;
				temp = node;
			} else {
				temp.next = node;
				temp = temp.next;
			}
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1 != null) {
			sum = l1.val + carry;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			l1 = l1.next;
			if (temp == null) {
				temp = node;
				continue;
			}
			temp.next = node;
			temp = temp.next;
		}
		while(l2 != null) {
			sum = l2.val + carry;
			carry = sum / 10;
			ListNode node = new ListNode(sum % 10);
			l2 = l2.next;
			if (temp == null) {
				temp = node;
				continue;
			}
			temp.next = node;
			temp = temp.next;
		}
		if (carry > 0) {
			if (temp == null)
				temp = new ListNode(carry);
			else
				temp.next = new ListNode(carry);
		}
		return ans;
    }
	
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Main End");
	}
}