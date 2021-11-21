package arrays.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchAnArrayTest {

    SearchInSortedRotatedArray<Integer> searchObject = new SearchInSortedRotatedArray<>();
    LinearSearch<Integer> linearSearchObject = new LinearSearch<>();

    @Test
    public void testSearchInSortedRotatedArray() {
        Integer[] sortedRotatedArray = new Integer[]{4, 5, 6, 7, 1, 2, 3};
        Assertions.assertEquals(3, searchObject.search(sortedRotatedArray, 7));
        Assertions.assertEquals(0, searchObject.search(sortedRotatedArray, 4));
        Assertions.assertEquals(6, searchObject.search(sortedRotatedArray, 3));
    }

    @Test
    public void searchInSortedArray() {
        Integer[] sortedArray = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Assertions.assertEquals(6, searchObject.search(sortedArray, 7));
        Assertions.assertEquals(0, searchObject.search(sortedArray, 1));
        Assertions.assertEquals(2, searchObject.search(sortedArray, 3));
    }

    @Test
    public void searchNonExistingElements() {
        Integer[] sortedRotatedArray = new Integer[]{4, 5, 6, 7, 1, 2, 3};
        Assertions.assertEquals(-1, searchObject.search(sortedRotatedArray, 0));
        Assertions.assertEquals(-1, searchObject.search(sortedRotatedArray, 10));

        Integer[] sortedArray = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        Assertions.assertEquals(-1, searchObject.search(sortedArray, 0));
        Assertions.assertEquals(-1, searchObject.search(sortedArray, 10));
    }

    @Test
    public void linearSearchInUnSortedArray() {
        Integer[] unSortedArray = new Integer[]{4, 6, 7, 1, 3, 5, 2};
        Assertions.assertEquals(2, linearSearchObject.indexOf(unSortedArray, 7));
        Assertions.assertEquals(-1, linearSearchObject.indexOf(unSortedArray, 10));
    }
}
