package org.buildwithraghu.linearDS;

import java.util.Arrays;
import java.util.Random;
import java.util.Spliterator;
import java.util.stream.IntStream;

public class DynamicArrays {

    private static Random random = new Random();

    public static void main(String[] args) {
        int[] arr = new int[10];
        IntStream.range(0, 10).forEach(i -> arr[i] = random.nextInt(0, 100));

        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        arr2[arr.length/2] = 111;
        // Returns 0 if the arr == arr2 else -1 or 1 based on the values
        System.out.println(Arrays.compare(arr, arr2));

        // This also works
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(arr3));

        // Spliterator is not thread-safe but still we can iterate large-data in parallel
        Spliterator<Integer> firsthalf = Arrays.spliterator(arr3);
        Spliterator<Integer> secondhalf = firsthalf.trySplit();

        System.out.println("First half --");
        while(firsthalf.tryAdvance(System.out::println)) {}

        System.out.println("Second half --");
        secondhalf.forEachRemaining(System.out::println);

        System.out.println("testing parallel streaming: " + Arrays.toString(arr3));
        Arrays.stream(arr3).boxed().toList().parallelStream().forEach(System.out::println);
    }
}
