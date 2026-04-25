package org.buildwithraghu.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // https://leetcode.com/problems/clone-graph
    HashMap<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        if (visited.containsKey(node))
            return visited.get(node);
        Node newGraph = new Node(node.val);
        visited.put(node, newGraph);
        for(Node nei: node.neighbors) {
            newGraph.neighbors.add(cloneGraph(nei));
        }
        return newGraph;
    }
}
