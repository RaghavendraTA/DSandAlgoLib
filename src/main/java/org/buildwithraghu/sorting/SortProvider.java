package org.buildwithraghu.sorting;

public class SortProvider {

    private static class SortValidator implements ISortValidator 
    {
        public void validate(int[] arr) throws Exception {
            for(int i = 1; i < arr.length; i++) {
                if (arr[i-1] > arr[i]) {
                    throw new Exception("✖ Array is not sorted");
                }
            }
            System.out.println("✓ Array is sorted");
        }
    }

    private static ISortingAlgo bubbleSort = new BubbleSort();
    private static ISortingAlgo mergeSort = new MergeSort();

    public static ISortingAlgo bubbleSort() { 
        return bubbleSort; 
    }

    public static ISortingAlgo mergeSort() { 
        return mergeSort; 
    }

    private static ISortValidator validator = new SortValidator();

    public static ISortValidator provideSortValidator() {
        return validator;
    }
}
