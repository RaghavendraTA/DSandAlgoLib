package org.buildwithraghu.lowleveldesign.ratelimiter;

import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimitConfig;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.StrategyType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterDemo {

    private static final String CLIENT_ID = "client-1";

    public static void main(String[] args) throws Exception {
        System.out.println("=== Fixed Window Strategy Demo (5 req/window) ===");
        demoFixedWindow();

        System.out.println("\n=== Token Bucket Strategy Demo (5 tokens, 1/sec refill) ===");
        demoTokenBucket();

        System.out.println("\n=== Leaky Bucket Strategy Demo (5 capacity, 1/sec drain) ===");
        demoLeakyBucket();

        System.out.println("\n=== Sliding Window Strategy Demo (5 req/window) ===");
        demoSlidingWindow();
    }

    private static void demoFixedWindow() throws Exception {
        RateLimiterManager manager = new RateLimiterManager();
        RateLimitConfig config = new RateLimitConfig(5, 1, 60000);

        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        AtomicInteger allowed = new AtomicInteger(0);
        AtomicInteger denied = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            final int idx = i;
            service.submit(() -> {
                boolean isAllowed = manager.isAllowed("api/users", CLIENT_ID, config, StrategyType.FIXED_WINDOW);
                synchronized (System.out) {
                    System.out.println("Request " + (idx + 1) + ": " + (isAllowed ? "ALLOWED" : "DENIED"));
                }
                if (isAllowed) allowed.incrementAndGet();
                else denied.incrementAndGet();
                latch.countDown();
            });
        }

        latch.await(10, TimeUnit.SECONDS);
        service.shutdown();
        manager.shutdown();
        System.out.println("Summary: " + allowed.get() + " allowed, " + denied.get() + " denied");
    }

    private static void demoTokenBucket() throws Exception {
        RateLimiterManager manager = new RateLimiterManager();
        RateLimitConfig config = new RateLimitConfig(5, 1, 60000);

        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(8);
        AtomicInteger allowed = new AtomicInteger(0);
        AtomicInteger denied = new AtomicInteger(0);

        for (int i = 0; i < 8; i++) {
            final int idx = i;
            service.submit(() -> {
                boolean isAllowed = manager.isAllowed("api/data", CLIENT_ID, config, StrategyType.TOKEN_BUCKET);
                synchronized (System.out) {
                    System.out.println("Request " + (idx + 1) + ": " + (isAllowed ? "ALLOWED" : "DENIED"));
                }
                if (isAllowed) allowed.incrementAndGet();
                else denied.incrementAndGet();
                latch.countDown();
            });
        }

        latch.await(10, TimeUnit.SECONDS);
        service.shutdown();
        manager.shutdown();
        System.out.println("Summary: " + allowed.get() + " allowed, " + denied.get() + " denied");
    }

    private static void demoLeakyBucket() throws Exception {
        RateLimiterManager manager = new RateLimiterManager();
        RateLimitConfig config = new RateLimitConfig(5, 1, 60000);

        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(7);
        AtomicInteger allowed = new AtomicInteger(0);
        AtomicInteger denied = new AtomicInteger(0);

        for (int i = 0; i < 7; i++) {
            final int idx = i;
            service.submit(() -> {
                boolean isAllowed = manager.isAllowed("api/stream", CLIENT_ID, config, StrategyType.LEAKY_BUCKET);
                synchronized (System.out) {
                    System.out.println("Request " + (idx + 1) + ": " + (isAllowed ? "ALLOWED" : "DENIED"));
                }
                if (isAllowed) allowed.incrementAndGet();
                else denied.incrementAndGet();
                latch.countDown();
            });
        }

        latch.await(10, TimeUnit.SECONDS);
        service.shutdown();
        manager.shutdown();
        System.out.println("Summary: " + allowed.get() + " allowed, " + denied.get() + " denied");
    }

    private static void demoSlidingWindow() throws Exception {
        RateLimiterManager manager = new RateLimiterManager();
        RateLimitConfig config = new RateLimitConfig(5, 1, 60000);

        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(8);
        AtomicInteger allowed = new AtomicInteger(0);
        AtomicInteger denied = new AtomicInteger(0);

        for (int i = 0; i < 8; i++) {
            final int idx = i;
            service.submit(() -> {
                boolean isAllowed = manager.isAllowed("api/search", CLIENT_ID, config, StrategyType.SLIDING_WINDOW);
                synchronized (System.out) {
                    System.out.println("Request " + (idx + 1) + ": " + (isAllowed ? "ALLOWED" : "DENIED"));
                }
                if (isAllowed) allowed.incrementAndGet();
                else denied.incrementAndGet();
                latch.countDown();
            });
        }

        latch.await(10, TimeUnit.SECONDS);
        service.shutdown();
        manager.shutdown();
        System.out.println("Summary: " + allowed.get() + " allowed, " + denied.get() + " denied");
    }
}
