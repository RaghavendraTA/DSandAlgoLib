package org.buildwithraghu.javafeatures.concurrentDS;

import java.util.concurrent.*;

public class BlockingQueueExample {

    private static final BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(5);

    private static void jobRunner() {
        Runnable producer = () -> {
            for(int i = 0; i < 20; i++) {
                try {
                    Thread.sleep(200);
                    arrayBlockingQueue.put((i+1)*10);
                    System.out.println("(" + i + ") Added = " + ((i+1)*10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        ExecutorService exs = Executors.newCachedThreadPool();
        exs.submit(producer);
        exs.submit(() -> {
            for(int i = 0; i < 20; i++) {
                try {
                    // take() infinite time method blocks the consumer task until the data available
                    // poll() with wait time will wait until value is available else returns null
                    // if I decrease 250 to < 200 it will fail, cause producer needs at least 200 to produce
                    System.out.println("(" + i + ") Got = " + arrayBlockingQueue.poll(250, TimeUnit.MILLISECONDS));
                } catch(InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });
        exs.shutdown();
    }

    public static void main(String[] args) {
        jobRunner();
    }
}
