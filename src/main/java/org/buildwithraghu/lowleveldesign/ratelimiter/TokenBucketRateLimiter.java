package org.buildwithraghu.lowleveldesign.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {
    private final long nanosPerToken;      // nanoseconds it takes to generate one token
    private final long capacity;           // max tokens in bucket
    private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(double tokensPerSecond, long capacity) {
        if (tokensPerSecond <= 0) throw new IllegalArgumentException("tokensPerSecond > 0");
        if (capacity <= 0) throw new IllegalArgumentException("capacity > 0");
        this.nanosPerToken = (long) (1_000_000_000L / tokensPerSecond);
        this.capacity = capacity;
    }

    /**
     * Attempt to allow 1 request for the given key.
     */
    public boolean allowRequest(String key) {
        long now = System.nanoTime();
        Bucket b = buckets.computeIfAbsent(key, k -> new Bucket(now, capacity, nanosPerToken));
        return b.tryConsume(now);
    }

    /**
     * Optional: remove stale buckets to avoid memory leak.
     * This simple garbage-collector removes buckets not used since 'idleNanos'.
     */
    public void cleanupIdleBuckets(long idleMillis) {
        long cutoff = System.nanoTime() - TimeUnit.MILLISECONDS.toNanos(idleMillis);
        for (String key : buckets.keySet()) {
            Bucket b = buckets.get(key);
            if (b == null) continue;
            // try to obtain lock and check lastRefillTimeNanos
            if (b.getLock().tryLock()) {
                try {
                    if (b.getLastRefillTimeNanos() < cutoff) {
                        buckets.remove(key, b);
                    }
                } finally {
                    b.getLock().unlock();
                }
            }
        }
    }
}
