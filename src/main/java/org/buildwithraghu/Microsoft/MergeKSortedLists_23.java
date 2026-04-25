import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
	
	// Priority Q solution
	public ListNode mergeKLists_PQ(ListNode[] lists) {
        if (lists.length == 0)
			return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
		for(ListNode node: lists) {
            if (node != null)
			    pq.offer(node);
		}
		ListNode root = new ListNode(-1);
		ListNode temp = root;
		
		while(!pq.isEmpty()) {
			temp.next = pq.poll();
			temp = temp.next;
			if (temp.next != null)
				pq.offer(temp.next);
		}
		temp.next = null;
		return root.next;
    }
	
	// devide and conquer solution
	/*
	list1	list2	list3	list4
	   \	 /		   \	 /
	    list3			list4
		      \		   /
				list5
	*/
	public ListNode mergeKLists(ListNode[] lists) {
		int amount = lists.length;
        int interval = 1;
        while (interval < amount) {
            for (int i = 0; i < amount - interval; i += interval * 2) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return amount > 0 ? lists[0] : null;
    }
	
	private ListNode merge(ListNode node1, ListNode node2) {
		ListNode root = new ListNode(-1);
		ListNode ans = root;
		while(node1 != null && node2 != null) {
			if (node1.val <= node2.val) {
				ans.next = node1;
				node1 = node1.next;
			} else {
				ans.next = node2;
				node2 = node2.next;
			}
			ans = ans.next;
		}
		if(node1 != null) {
			ans.next = node1;
		} else if (node2 != null) {
			ans.next = node2;
		}
		return root.next;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}