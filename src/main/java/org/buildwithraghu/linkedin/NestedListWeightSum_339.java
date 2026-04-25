package org.buildwithraghu.linkedin;

import java.util.List;

public class NestedListWeightSum_339 {

    interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        void setInteger(int value);
        void add(NestedInteger ni);
        List<NestedInteger> getList();
    }

    int ans = 0;

    public int depthSum(List<NestedInteger> nestedList) {
        goDeep(nestedList, 1);
        return ans;
    }

    public void goDeep(List<NestedInteger> nestedList, int depth) {
        for(NestedInteger ni: nestedList) {
            if (ni.isInteger()) {
                ans += ni.getInteger() * depth;
            } else {
                goDeep(ni.getList(), depth + 1);
            }
        }
    }
}
