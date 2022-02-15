package trees.binarytrees;

import arrays.cache.Node;

import java.util.*;

public class Paths {

    // Print all Paths from root-to-leaf
    List<String> ans = new ArrayList<>();

    public void construct(List<Integer> nodes) {
        ans.add(String.join("->", nodes.stream().map(String::valueOf).toList()));
    }

    public void treePaths(Node root, Stack<Integer> nodes) {
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            nodes.push(root.value);
            construct(nodes);
            nodes.pop();
            return;
        }
        nodes.push(root.value);
        treePaths(root.left, nodes);
        treePaths(root.right, nodes);
        nodes.pop();
    }

    public List<String> binaryTreePaths(Node root) {
        ans.clear();
        treePaths(root, new Stack<>());
        return ans;
    }
}
