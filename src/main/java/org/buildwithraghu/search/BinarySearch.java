package org.buildwithraghu.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static boolean search(List<Integer> a, int s) {
        int l = 0, r = a.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (s == a.get(m))
                return true;
            if (s < a.get(m))
                r = m - 1;
            else
                l = m + 1;
        }
        return false;
    }

    public static int leftBound(List<Integer> a, int s) {
        int l = 0, r = a.size();
        while (l < r) {
            int m = l + (r - l) / 2;
            if (s < a.get(m))
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 20; i += 2) {
            a.add(i);
        }

        int v = 11;
        int idx = leftBound(a, v);
        System.out.println("Left Bound : " + v + " = " + idx);
        a.add(idx, v);
        System.out.println(a);
    }
}
