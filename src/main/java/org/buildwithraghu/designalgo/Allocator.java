package org.buildwithraghu.designalgo;

import java.util.*;

// https://leetcode.com/problems/design-memory-allocator/
public class Allocator {

    Integer[] arr;
    int capacity = 0;

    public Allocator(int n) {
        capacity = n;
        arr = new Integer[n];
        Arrays.fill(arr, null);
    }

    // O(N)
    private int getIndex(int size) {
        int c = 0, idx = -1;
        for(int i = 0; i < capacity; i++) {
            if (arr[i] == null) {
                c++;
                if (idx == -1) idx = i;
            } else {
                c = 0;
                idx = -1;
            }
            if (c == size)
                return idx;
        }
        return c == size ? idx : -1;
    }

    // O(N)
    public int allocate(int size, int mID) {
        int idx = getIndex(size);
        if (idx < 0)
            return -1;
        for(int i = idx; i < (idx + size); i++)
            arr[i] = mID;
        return idx;
    }

    // O(N)
    public int freeMemory(int mID) {
        Integer id = mID;
        int c = 0;
        for(int i = 0; i < capacity; i++) {
            if (arr[i] != null && arr[i].equals(id)) {
                arr[i] = null;
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Allocator all = new Allocator(5);
        all.arr[2] = 30;
        System.out.println(Arrays.toString(all.arr));
        System.out.println(all.getIndex(2));
    }
}
