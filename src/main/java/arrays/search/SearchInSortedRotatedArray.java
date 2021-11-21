package arrays.search;

/**
 * Find a pivot using binary search
 *
 * (end-start == 0)                             => not found
 * (end-start == 1 and arr[start] == arr[end])  => return start;
 * (mid - 1 >= 0 && arr[mid-1] > arr[mid])      => return mid;
 * (mid + 1 >= 0 && arr[mid] > arr[mid+1])      => return mid + 1;
 *
 * => max(getPivot(start, mid-1), gitPivot(mid, end));
 */

public class SearchInSortedRotatedArray<T extends Comparable<T>> {

    private int getPivot(T[] numbs, int start, int end) {

        if (end - start == 0) {
            return -1;
        }
        else if (end - start == 1) {
            return numbs[start].compareTo(numbs[end]) > 0 ? start : -1;
        }
        int mid = (start + end) / 2;
        if (mid - 1 >= 0 && numbs[mid - 1].compareTo(numbs[mid]) > 0) {
            return mid;
        }
        else if (mid + 1 <= end && numbs[mid].compareTo(numbs[mid + 1]) > 0) {
            return mid + 1;
        }
        return Math.max(getPivot(numbs, start, mid - 1), getPivot(numbs, mid, end));
    }

    public int search(T[] arr, T target) {

        int p = getPivot(arr, 0, arr.length - 1);
        BinarySearch<T> binarySearch = new BinarySearch<>();
        if (p == -1) {
            return binarySearch.indexOf(arr, target);
        }
        else {
            return Math.max(
                    binarySearch.search(arr, target, 0, p - 1),
                    binarySearch.search(arr, target, p, arr.length - 1)
            );
        }
    }

}
