import java.util.HashMap;
import java.util.Map;

class LFUCache {

    static class Node {
        int key, value, freq;
        Node next, prev;
        Node(int key, int val) {
            this.key = key;
            this.value = val;
            this.freq = 1;
            next = null;
            prev = null;
        }
    }

    static class DLL {
        private Node head, tail;

        DLL() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void insertAtEnd(Node node) {
            tail.prev.next = node;
            node.prev = tail.prev;

            tail.prev = node;
            node.next = tail;
        }

        public void remove(Node node) {
            Node left = node.prev;
            Node right = node.next;
            left.next = right;
            right.prev = left;
        }

        public Node removeAtFront() {
            Node node = head.next;
            head.next = node.next;
            node.next.prev = head;
            return node;
        }

        public boolean hasValue() {
            return head.next != tail;
        } 
    }

    Map<Integer, Node> cache;
    Map<Integer, DLL> freqMap;
    int capacity, minCount = 1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);
        freqMap.get(node.freq).remove(node);
        if (!freqMap.get(node.freq).hasValue()) {
            freqMap.remove(node.freq);

            if (minCount == node.freq)
                minCount++;
        }
        node.freq++;
        freqMap.putIfAbsent(node.freq, new DLL());
        freqMap.get(node.freq).insertAtEnd(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            freqMap.get(node.freq).remove(node);
            if (!freqMap.get(node.freq).hasValue()) {
                freqMap.remove(node.freq);
                if (minCount == node.freq)
                    minCount++;
            }
            node.freq++;
            node.value = value;
            freqMap.putIfAbsent(node.freq, new DLL());
            freqMap.get(node.freq).insertAtEnd(node);
            return;
        }

        if (cache.size() == capacity) {
            DLL dll = freqMap.get(minCount);
            Node node = dll.removeAtFront();
            if (!dll.hasValue()) {
                freqMap.remove(minCount);
            }
            cache.remove(node.key);
        }

        Node node = new Node(key, value);
        minCount = 1;
        freqMap.putIfAbsent(1, new DLL());
        freqMap.get(1).insertAtEnd(node);
        cache.put(key, node);
    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println("Testing");
	}
}