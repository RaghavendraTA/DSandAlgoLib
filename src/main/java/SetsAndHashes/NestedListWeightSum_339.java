package SetsAndHashes;

import java.util.*;

public class NestedListWeightSum_339 {

    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public void setInteger(int value);
        public void add(NestedInteger ni);
        public List<NestedInteger> getList();
    }

    int ans = 0;

    public int depthSum(List<NestedInteger> nestedList) {
        depthSum(nestedList, 1);
        return ans;
    }

    public void depthSum(List<NestedInteger> ls, int depth) {
        for(NestedInteger n: ls) {
            if (n.isInteger()) {
                ans += n.getInteger() * depth;
            } else {
                depthSum(n.getList(), depth + 1);
            }
        }
    }
}
