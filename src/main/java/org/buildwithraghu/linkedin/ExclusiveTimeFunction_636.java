package org.buildwithraghu.linkedin;

import java.util.*;

public class ExclusiveTimeFunction_636 {

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        List<int[]> store = new ArrayList<>();

        for (String s : logs) {
            String[] ss = s.split(":");
            int funcId = Integer.parseInt(ss[0]);
            int time = Integer.parseInt(ss[2]);
            if (store.isEmpty()) {
                store.addLast(new int[]{funcId, time});
            } else if ("start".equals(ss[1])) {
                int[] last = store.getLast();
                ans[last[0]] += time - last[1];
                store.addLast(new int[]{funcId, time});
            } else { // end
                int[] last = store.removeLast();
                ans[last[0]] += time - last[1] + 1;
                if (!store.isEmpty()) {
                    store.getLast()[1] = time + 1;
                }
            }
        }
        return ans;
    }

    static void main() {
        ExclusiveTimeFunction_636 ex = new ExclusiveTimeFunction_636();
        int n = 1;
        List<String> logs = Arrays.stream(new String[]{"0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"}).toList();
        System.out.println(Arrays.toString(ex.exclusiveTime(n, logs)));
    }
}
