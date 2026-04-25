package org.buildwithraghu.lowleveldesign.ratelimiter.implementation;

import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimitConfig;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimiterStrategy;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowStrategy implements RateLimiterStrategy {

    private final long maxRequests;
    private final long windowDurationMs;

    // key -> queue of request timestamps
    private final Map<String, Queue<Long>> windows = new ConcurrentHashMap<>();

    public SlidingWindowStrategy(RateLimitConfig config) {
        this.maxRequests = config.getMaxCapacity();
        this.windowDurationMs = config.getWindowDurationMs();
    }

    @Override
    public boolean allowRequest(String key, long windowDurationMs) {
        long now = System.currentTimeMillis();
        final boolean[] allowed = {false};
        windows.compute(key, (k, existing) -> {
            if (existing == null) {
                existing = new ConcurrentLinkedQueue<>();
            }
            // Remove expired timestamps
            while (!existing.isEmpty() && (now - existing.peek()) > windowDurationMs) {
                existing.poll();
            }
            if (existing.size() < maxRequests) {
                existing.offer(now);
                allowed[0] = true;
            }
            return existing;
        });
        return allowed[0];
    }

    @Override
    public void reset(String key) {
        windows.remove(key);
    }

    @Override
    public void cleanup() {
        long now = System.currentTimeMillis();
        windows.entrySet().removeIf(entry -> {
            Queue<Long> queue = entry.getValue();
            while (!queue.isEmpty() && (now - queue.peek()) > windowDurationMs) {
                queue.poll();
            }
            return queue.isEmpty();
        });
    }
}
