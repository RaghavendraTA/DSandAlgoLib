package org.buildwithraghu;

import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int prev = 0, i = 0;
        while(i < nums.length) {
            while (i < nums.length && nums[prev] == nums[i]) {
                i++;
            }
            prev++;
            if (i < nums.length && prev < nums.length)
                nums[prev] = nums[i];
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println("Testing");
    }
}
