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
        long now = System.currentTimeMillis();
        WindowState state = windows.compute(key, (k, existing) -> {
            if (existing == null || (now - existing.windowStart) >= windowDurationMs) {
                // New window — always allow first request
                return new WindowState(1, now, true);
            }
            if (existing.count < maxRequests) {
                existing.count++;
                return new WindowState(existing.count, now, true);
            }
            // Over limit — don't increment, deny
            return new WindowState(existing.count, existing.windowStart, false);
        });
        return state.allowed;
    }

    @Override
    public void reset(String key) {
        windows.remove(key);
    }

    @Override
    public void cleanup() {
        long now = System.currentTimeMillis();
        windows.entrySet().removeIf(entry -> (now - entry.getValue().windowStart) > windowDurationMs * 2);
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
