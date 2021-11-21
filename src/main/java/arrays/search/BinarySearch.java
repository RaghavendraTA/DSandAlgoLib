package arrays.search;

public class BinarySearch<T extends Comparable<T>> {

    public int indexOf(T[] arr, T target) {

        return search(arr, target, 0, arr.length - 1);
    }

    public boolean contains(T[] arr, T target) {

        return search(arr, target, 0, arr.length - 1) != -1;
    }

    public int search(T[] nums, T key, int start, int end) {

        if (end < start) {
            return -1;
        }
        else if (end - start == 0 || end - start == 1) {
            return key.compareTo(nums[start]) == 0 ? start : key.compareTo(nums[end]) == 0 ? end : -1;
        }
        int mid = (start + end) / 2;

        if (key.compareTo(nums[mid]) == 0) {
            return mid;
        }
        else if (key.compareTo(nums[mid]) < 0) {
            return search(nums, key, start, mid - 1);
        }
        else {
            return search(nums, key, mid, end);
        }
    }

    // End of the program

}
