package org.buildwithraghu.linkedin;

import java.util.*;

public class AccountsMerge_721 {

    static class UnionFindAccountsMerge {
        int[] parent, rank;

        UnionFindAccountsMerge(int n) {
            rank = new int[n];
            parent = new int[n];
            for(int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY)
                return;

            if (rank[parentX] < rank[parentY])
                parent[parentX] = parentY;
            else if (rank[parentX] > rank[parentY])
                parent[parentY] = parentX;
            else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        UnionFindAccountsMerge uf = new UnionFindAccountsMerge(n);

        Map<String, Integer> emailToIndex = new HashMap<>();

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailToIndex.containsKey(email)) {
                    uf.union(i, emailToIndex.get(email));
                } else {
                    emailToIndex.put(email, i);
                }
            }
        }

        Map<Integer, Set<String>> merged = new HashMap<>();

        for(Map.Entry<String, Integer> entry: emailToIndex.entrySet()) {
            String email = entry.getKey();
            int idx = entry.getValue();
            int parent = uf.find(idx);
            merged.computeIfAbsent(parent, p -> new TreeSet<>()).add(email);
        }

        List<List<String>> ans = new ArrayList<>();

        for(Map.Entry<Integer, Set<String>> entry: merged.entrySet()){
            int idx = entry.getKey();
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(idx).getFirst());
            temp.addAll(entry.getValue());
            ans.add(temp);
        }

        return ans;
    }
}
