package org.buildwithraghu.concurrency;

import java.util.concurrent.CompletableFuture;

public class AsyncThreadWithResult {

    static void main(String[] args) throws Exception {

        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> {
                    try { Thread.sleep(1000); } catch (Exception e) {}
                    return 10 + 20;
                });

        System.out.println("Back to Main Thread");
        Integer result = future.get(); // waits
        System.out.println(result);
    }
}