package org.buildwithraghu.lowleveldesign.ratelimiter.implementation;

import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimitConfig;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimiterStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimiterStrategy {

    private final long maxRequests;
    private final long windowDurationMs;

    // key -> { count, windowStart }
    private final Map<String, WindowState> windows = new ConcurrentHashMap<>();

    public FixedWindowStrategy(RateLimitConfig config) {
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

    private static class WindowState {
        int count;
        long windowStart;
        boolean allowed;

        WindowState(int count, long windowStart, boolean allowed) {
            this.count = count;
            this.windowStart = windowStart;
            this.allowed = allowed;
        }
    }
}
