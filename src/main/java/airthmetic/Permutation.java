package airthmetic;

/*
 * created by raghavendra.ta on 22-Dec-2021
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

    List<List<Integer>> result = new ArrayList<>();

    void swap(List<Integer> arr, int i, int j) {
        Integer temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    void reverseArray(List<Integer> arr, Integer start, Integer end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    void nextPermutation(List<Integer> numbs) {
        int i = numbs.size() - 2;
        while (i >= 0 && numbs.get(i + 1) <= numbs.get(i)) {
            i--;
        }
        if (i >= 0) {
            int j = numbs.size() - 1;
            while (numbs.get(j) <= numbs.get(i)) {
                j--;
            }
            swap(numbs, i, j);
        }
        reverseArray(numbs, i + 1, numbs.size() - 1);
    }

    boolean isEqual(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) return false;
        int n = a.size();
        for(int i = 0; i < n; i++) {
            if (a.get(i).intValue() != b.get(i).intValue()) return false;
        }
        return true;
    }

    List<List<Integer>> permute(List<Integer> nums) {
        List<Integer> temp = new ArrayList<>(nums);
        result.add(nums);
        nextPermutation(temp);
        while(!isEqual(temp, nums)) {
            result.add(temp);
            temp = new ArrayList<>(temp);
            nextPermutation(temp);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> ls = Arrays.asList(1, 2, 3);
        for(List<Integer> t: new Permutation().permute(ls)) {
            System.out.println(t);
        }
    }
}
