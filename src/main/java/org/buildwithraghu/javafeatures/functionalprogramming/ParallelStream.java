package org.buildwithraghu.javafeatures.functionalprogramming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStream {

    private Stream<String> getLargeStringList(int n, int wstart, int wend) {
        Random random = new Random();
        return Stream.generate(() -> Stream
            .generate(() -> String.valueOf((char)('a' + random.nextInt(26))))
            .limit(random.nextInt(wstart, wend))
            .collect(Collectors.joining())
        ).limit(n);
    }

    public void test() {
        Stream<String> strings = getLargeStringList(1000, 2, 8);
        //strings.forEach(System.out::println);
        int cnt = strings.parallel().map(String::length).reduce(Integer::sum).orElse(0);
        System.out.println(cnt);
    }

    public void iterate() {
        Object[] ans = Stream.iterate(1.0, p -> p * 2)
                .peek(System.out::println)
                .limit(10).toArray();
    }

    public static void main(String[] args) {
        ParallelStream ps = new ParallelStream();
        Stream<String> str = ps.getLargeStringList(10, 4, 7);
        Iterator<String> itr = str.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        // System.out.println(str.max(String::compareToIgnoreCase).orElse("Nothing is big"));
        // ps.test();
        // ps.iterate();
    }
}
