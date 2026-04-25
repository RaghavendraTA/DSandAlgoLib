package org.buildwithraghu.concurrency;

import java.util.concurrent.CompletableFuture;

public class SimpleAsync {

    static void main(String[] args) {

        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Running async task: " + i);
                try { Thread.sleep(500); } catch (Exception e) {}
            }
        });

        System.out.println("Main thread finished");

        try { Thread.sleep(3000); } catch (Exception e) {}
    }
}