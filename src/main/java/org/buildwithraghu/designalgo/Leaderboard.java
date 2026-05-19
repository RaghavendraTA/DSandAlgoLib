package org.buildwithraghu.designalgo;

import java.util.*;

public class Leaderboard {

    static class Node {
        int pid, score;
        Node(int pid, int score) {
            this.pid = pid;
            this.score = score;
        }
        @Override
        public String toString() {
            return "[pid=" + pid + ",score=" + score +"]";
        }
    }

    List<Node> board;
    Map<Integer, Node> map;

    public Leaderboard() {
        board = new ArrayList<>();
        map = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        if (map.containsKey(playerId)) {
            Node old = map.get(playerId);
            board.remove(old);
            old.score += score;
            int idx = insertionPoint(old.score);
            board.add(idx, old);
        } else {
            Node node = new Node(playerId, score);
            int idx = insertionPoint(score);
            board.add(idx, node);
            map.put(playerId, node);
        }
    }

    public int top(int K) {
        int ans = 0;
        for (int i = board.size() - 1; i >= 0 && K > 0; i--, K--) {
            ans += board.get(i).score;
        }
        return ans;
    }

    public void reset(int playerId) {
        Node node = map.remove(playerId);
        if (node != null) {
            board.remove(node);
        }
    }

    private int insertionPoint(int score) {
        int low = 0;
        int high = board.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (board.get(mid).score <= score) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
