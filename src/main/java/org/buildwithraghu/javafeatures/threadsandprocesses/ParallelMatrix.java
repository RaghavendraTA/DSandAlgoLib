package org.buildwithraghu.javafeatures.threadsandprocesses;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelMatrix {

    public static List<List<Integer>> multiply(List<List<Integer>> mat1, List<List<Integer>> mat2) {
        int cols1 = mat1.getFirst().size();
        int cols2 = mat2.getFirst().size();

        // Transpose mat2 for easier column access
        List<List<Integer>> mat2Transposed = new ArrayList<>();
        for (int j = 0; j < cols2; j++) {
            int colIndex = j;
            List<Integer> col = mat2.stream()
                .map(row -> row.get(colIndex))
                .collect(Collectors.toList());
            mat2Transposed.add(col);
        }

        return mat1.stream().parallel().map(row ->
            mat2Transposed.stream().map(col ->
                IntStream.range(0, cols1).map(k -> row.get(k) * col.get(k)).sum()
            ).toList()
        ).toList();
    }

    private static List<List<Integer>> multiplyNonParallel(List<List<Integer>> mat1, List<List<Integer>> mat2) {
        int rows1 = mat1.size();
        int cols1 = mat1.getFirst().size();
        int cols2 = mat2.getFirst().size();

        // Initialize result matrix with zeros
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows1; i++) {
            result.add(IntStream.range(0, cols2).map(x -> 0)
                  .boxed()
                  .collect(Collectors.toCollection(ArrayList::new))
            );
        }

        // Perform matrix multiplication
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                int sum = 0;
                for (int k = 0; k < cols1; k++) {
                    sum += mat1.get(i).get(k) * mat2.get(k).get(j);
                }
                result.get(i).set(j, sum);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int N = 1000;
        List<List<Integer>> mat1 = IntStream.range(0, N)
                .mapToObj(i -> IntStream.range(0, N).boxed().toList())
                .toList();

        List<List<Integer>> mat2 = IntStream.range(0, N)
                .mapToObj(i -> IntStream.range(0, N).boxed().toList())
                .toList();

        // Measure time
        long start = System.currentTimeMillis();
        List<List<Integer>> result = ParallelMatrix.multiply(mat1, mat2);
        long end = System.currentTimeMillis();

        System.out.println("Elapsed time using parallel: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        result = ParallelMatrix.multiplyNonParallel(mat1, mat2);
        end = System.currentTimeMillis();

        System.out.println("Elapsed time without parallelism: " + (end - start) + " ms");
    }
}
