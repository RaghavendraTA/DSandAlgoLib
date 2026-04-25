package org.buildwithraghu.javafeatures.functionalprogramming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {

    // Stream class
    public static Stream<Integer> testIntStream() {
        return IntStream.range(0, 100).boxed();
    }

    // Comparator
    public static void testComparator(List<Integer> someList) {
        someList.sort(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        someList.sort((o1, o2) -> Integer.compare(o2, o1));
        System.out.println(someList);
        someList.sort(Comparator.comparingInt(x -> x));
        someList.sort(Comparator.naturalOrder());
        System.out.println(someList);
    }

    public static ArrayList<String> streamOps(List<String> list) {
        return list.stream()
                .filter(x -> !x.startsWith("raghu"))
                .map(s -> s.substring(0, 4))
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void streamOps2(IntStream list) {
        int sum = list.map(x -> x * 2).sum();
        int max = list.map(x -> -x).max().getAsInt();

    }

    public static boolean compare(List<Integer> list1, List<Integer> list2) {
        return list1.stream().anyMatch(x -> !list2.contains(x));
    }

    public static void main(String[] args) {
        testComparator(Arrays.asList(89, 2, 7, 3, 6, 5));
    }
}
