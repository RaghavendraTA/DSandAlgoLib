package org.buildwithraghu.lowleveldesign.ratelimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiterDemo {

    public static void main(String[] args) throws InterruptedException {
        // e.g., 5 tokens/sec, capacity 10
        TokenBucketRateLimiter rl = new TokenBucketRateLimiter(5.0, 10);

        String key = "user-123";

        // simulate bursts and steady load
        ExecutorService ex = Executors.newFixedThreadPool(8);
        Runnable task = () -> {
            boolean allowed = rl.allowRequest(key);
            System.out.println(Thread.currentThread().getName() + " allowed=" + allowed + " at " + System.currentTimeMillis()%100000);
        };

        // burst of 12 requests immediately
        for (int i = 0; i < 12; i++) ex.submit(task);

        Thread.sleep(1200); // ~1.2 sec -> ~5 tokens refilled
        System.out.println("---- after 1.2s ----");

        for (int i = 0; i < 8; i++) ex.submit(task);

        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);
    }
}
