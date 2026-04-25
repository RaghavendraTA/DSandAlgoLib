package org.buildwithraghu.linkedin;

import org.buildwithraghu.utils.TreeNode;

import java.util.*;

public class ClosestBinarySearchTreeII {

    // This solution coses O(n log(n))
    static class Pair {
        Integer key;
        Double value;
        Pair(Integer i, Double d) {
            key = i;
            value = d;
        }
        @Override
        public String toString() {
            return "key = " + key + ", value = " + value;
        }
    }

    PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(x -> x.value));

    public List<Integer> closestKValues_nlogn(TreeNode root, double target, int k) {
        traverse(root, target);
        List<Integer> ans = new ArrayList<>();
        while(k > 0 && !pq.isEmpty()) {
            ans.add(pq.poll().key);
            k--;
        }
        return ans;
    }

    private void traverse(TreeNode node, double target) {
        if (node == null)
            return;
        double diff = Math.abs(((double)node.val) - target);
        pq.offer(new Pair(node.val, diff));
        traverse(node.left, target);
        traverse(node.right, target);
    }

    // O(n)

    private void inorder(TreeNode root, double target, int k, Deque<Integer> window){
        if(root == null) return;

        inorder(root.left, target, k, window);

        if(window.size() == k){
            double left_diff = Math.abs(target - (double)window.getFirst());
            double right_diff = Math.abs(target - (double)root.val);
            if(right_diff < left_diff){
                window.removeFirst();
                window.addLast(root.val);
            } else {
                return;
            }
        } else {
            window.addLast(root.val);
        }

        inorder(root.right, target, k, window);
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> window = new LinkedList<>();
        inorder(root, target, k, window);
        for(Integer val: window) result.add(val);
        return result;
    }
}


