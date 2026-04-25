package org.buildwithraghu.linkedin;

import java.util.List;

public class NestedListWeightSumII_364 {

    // For reference only
    interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        void setInteger(int value);
        void add(NestedInteger ni);
        List<NestedInteger> getList();
    }

    int maxDepth = 0, ans = 0;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        getMaxDepth(nestedList, 1);
        goDeep(nestedList, 1);
        return ans;
    }

    public void getMaxDepth(List<NestedInteger> nestedList, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        for(NestedInteger ni: nestedList) {
            if (!ni.isInteger()) {
                getMaxDepth(ni.getList(), depth + 1);
            }
        }
    }

    public void goDeep(List<NestedInteger> nestedList, int depth) {
        for(NestedInteger ni: nestedList) {
            if (ni.isInteger()) {
                ans += ni.getInteger() * (maxDepth - depth + 1);
            } else {
                goDeep(ni.getList(), depth + 1);
            }
        }
    }
}
