package stack;

import java.util.*;

public class MaxStack_716 {

    static class MaxStack {

        static class Node {
            int val, id;
            Node(int v, int i) { val = v; id = i; }
        }

        Deque<Node> stack;
        PriorityQueue<Node> pq;
        Set<Integer> removed;
        int id = 0;

        public MaxStack() {
            this.pq = new PriorityQueue<>((a, b) -> b.val != a.val ? b.val - a.val : b.id - a.id);
            this.stack = new ArrayDeque<>();
            this.removed = new HashSet<>();
        }

        public void push(int x) {
            Node n = new Node(x, id++);
            stack.push(n);
            pq.add(n);
        }

        public int pop() {
            cleanStack();
            Node n = stack.pop();
            removed.add(n.id);
            return n.val;
        }

        public int top() {
            cleanStack();
            return stack.peek().val;
        }

        public int peekMax() {
            cleanPQ();
            return pq.peek().val;
        }

        public int popMax() {
            cleanPQ();
            Node n = pq.poll();
            removed.add(n.id);
            return n.val;
        }

        private void cleanStack() {
            while (!stack.isEmpty() && removed.contains(stack.peek().id)) {
                stack.pop();
            }
        }

        private void cleanPQ() {
            while (!pq.isEmpty() && removed.contains(pq.peek().id)) {
                pq.poll();
            }
        }
    }

    public static void main(String[] args) {
        MaxStack mx = new MaxStack();
        mx.push(5);
        mx.push(1);
        mx.push(5);
        System.out.println(mx.top());
        System.out.println(mx.popMax());
        System.out.println(mx.top());
        System.out.println(mx.peekMax());
        System.out.println(mx.pop());
        System.out.println(mx.top());
    }
}
