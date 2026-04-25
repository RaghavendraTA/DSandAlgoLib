package org.buildwithraghu.javafeatures.threadsandprocesses;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Interruption {

    public static void generateInfiniteNumber() {
        Runnable infiniteTask = () -> {
            int i = 0;
            try {
                while(true) {
                    Thread.sleep(200);
                    if (Thread.currentThread().isInterrupted()) return;
                    System.out.println(i);
                    i++;
                    if (i == 50)
                        Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService exs = Executors.newCachedThreadPool();
        exs.submit(infiniteTask);
        exs.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        generateInfiniteNumber();
    }
}
