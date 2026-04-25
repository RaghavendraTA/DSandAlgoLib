import java.util.HashMap;
import java.util.Map;

class Solution {
	
	public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Map<Node, Node> mapping = new HashMap<>();

        Node current = head;
        while(current != null) {
            mapping.put(current, new Node(current.val));
            current = current.next;
        }

        Node node;
        current = head;
        while(current != null) {
            node = mapping.get(current);
            node.next = mapping.get(current.next);
            node.random = mapping.get(current.random);
            current = current.next;
        }
        return mapping.get(head);
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}