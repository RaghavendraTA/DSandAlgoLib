package org.buildwithraghu.javafeatures.concurrentDS;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapExample {

    private static ConcurrentHashMap<Integer, Integer> cmap = new ConcurrentHashMap<>();

    public static void jobRunner() {
        Runnable producer = () -> {
            for(int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(200);
                    cmap.putIfAbsent(i, i*10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ExecutorService exs = Executors.newCachedThreadPool();
        exs.submit(producer);
        exs.submit(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 1000; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.println(cmap.get(i));
                    } catch(InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }
        });
        exs.shutdown();
    }

    public static void main(String[] args) {
        jobRunner();
    }
}
