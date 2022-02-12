package airthmetic;

/*
 * created by raghavendra.ta on 21-Dec-2021
 */

import java.util.*;

public class CombinationNcROfDigits {

    private final List<List<Integer>> temp = new ArrayList<>();

    void combineUtil(Stack<Integer> subset, int j, int n, int r) {
        if (subset.size() == r) {
            temp.add(new ArrayList<>(subset));
            return;
        }
        for(int i = j; i <= n; i++) {
            subset.add(i);
            combineUtil(subset, i + 1, n, r);
            subset.pop();
        }
    }

    List<List<Integer>> combine(int n, int r) {
        temp.clear();
        if (r == 0) {
            return temp;
        }
        combineUtil(new Stack<>(), 1, n, r);
        return temp;
    }

    public static void main(String[] args) {
        CombinationNcROfDigits ncr = new CombinationNcROfDigits();
        for(List<Integer> ls: ncr.combine(4, 3)) {
            System.out.println(ls);
        }
    }
}
