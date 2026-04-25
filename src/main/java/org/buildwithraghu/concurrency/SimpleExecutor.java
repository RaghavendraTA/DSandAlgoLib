package org.buildwithraghu.concurrency;

import org.buildwithraghu.utils.Pair;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class SimpleExecutor {

    static Function<Pair<Integer, Integer>, Void> processor = (Pair<Integer, Integer> pair) -> {
        int id = pair.key;
        int sleep = pair.value;

        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(sleep);
                System.out.println("Thread(" + id + ") = " + i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    };

    static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        try {
            service.submit(() -> SimpleExecutor.processor.apply(new Pair<>(1, 200)));
            service.submit(() -> SimpleExecutor.processor.apply(new Pair<>(2, 100)));
        } finally {
            service.shutdown();
        }
    }
}