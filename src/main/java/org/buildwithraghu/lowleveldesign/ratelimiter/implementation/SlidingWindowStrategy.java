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
        return false;
    }

    @Override
    public void reset(String key) {
        
    }

    @Override
    public void cleanup() {
        
    }
}
