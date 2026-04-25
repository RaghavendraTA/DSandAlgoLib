package org.buildwithraghu.google;

class AddTwoNumbers_2 {

    public AddTwoNumbers_2.ListNode addTwoNumbers(AddTwoNumbers_2.ListNode l1, AddTwoNumbers_2.ListNode l2) {
        AddTwoNumbers_2.ListNode ans = null, temp = null;
		int sum = 0, carry = 0;
        while(l1 != null && l2 != null) {
			sum = l1.val + l2.val + carry;
			carry = sum / 10;
			AddTwoNumbers_2.ListNode node = new AddTwoNumbers_2.ListNode(sum % 10);
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
			AddTwoNumbers_2.ListNode node = new AddTwoNumbers_2.ListNode(sum % 10);
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
			AddTwoNumbers_2.ListNode node = new AddTwoNumbers_2.ListNode(sum % 10);
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
				temp = new AddTwoNumbers_2.ListNode(carry);
			else
				temp.next = new AddTwoNumbers_2.ListNode(carry);
		}
		return ans;
    }

	public static void main(String[] args) {
		AddTwoNumbers_2 s = new AddTwoNumbers_2();
		System.out.println("Main End");
	}

	static class ListNode {
		public int val;
		public ListNode next;
		public ListNode() {}
		public ListNode(int val) { this.val = val; }
		public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}