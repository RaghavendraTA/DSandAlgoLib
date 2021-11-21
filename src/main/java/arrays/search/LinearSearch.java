package arrays.search;

public class LinearSearch<T extends Comparable<T>> {

    public boolean isContains(T[] arr, T key) {

        return indexOf(arr, key) != -1;
    }

    public int indexOf(T[] arr, T key) {

        int contains = -1;
        for (int i = 0; i < arr.length; i++) {
            if (key.compareTo(arr[i]) == 0) {
                contains = i;
                break;
            }
        }
        return contains;
    }
}
