package org.buildwithraghu.google;

class MergeSortedArray_88 {
	
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i = m + n - 1;
		int p = m-1, q = n-1;
		while(p >= 0 && q >= 0 && i >= 0) {
			nums1[i--] = nums1[p] > nums2[q] ? nums1[p--] : nums2[q--];
		}
		while(p >= 0 && i >= 0) {
			nums1[i--] = nums1[p--];
		}
		while(q >= 0 && i >= 0) {
			nums1[i--] = nums2[q--];
		}
    }

	public static void main(String[] args) {
		MergeSortedArray_88 s = new MergeSortedArray_88();
		System.out.println("Testing");
	}
}