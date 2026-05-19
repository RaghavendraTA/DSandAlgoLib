package org.buildwithraghu.lists;

public class MyLinkedList {

    int[] arr = new int[1001];
    int i = 0, j = 0;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (index > j)
            return -1;
        return arr[index];
    }

    public void addAtHead(int val) {

    }

    public void addAtTail(int val) {

    }

    public void addAtIndex(int index, int val) {

    }

    public void deleteAtIndex(int index) {

    }
}
