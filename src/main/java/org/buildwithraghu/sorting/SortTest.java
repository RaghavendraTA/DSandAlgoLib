package arrays.sort;

import java.util.Random;

public class SortTest {
    
    public static void main(String[] args) throws Exception {
        int MAXLEN = 100;
        int[] arr = new int[MAXLEN];
        Random random = new Random();
        
        // Inserting bounded random numbers
        for(int i = 0; i < MAXLEN; i++) {
            arr[i] = random.nextInt(MAXLEN);
        }

        // Sort
        SortProvider.bubbleSort().sort(arr);

        // Verify
        SortProvider.provideSortValidator().validate(arr);
    }
}
