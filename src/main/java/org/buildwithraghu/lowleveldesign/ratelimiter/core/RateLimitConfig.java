package org.buildwithraghu.lowleveldesign.ratelimiter.core;

public class RateLimitConfig {
    private final long maxCapacity;
    private final long refillRatePerSecond;
    private final long windowDurationMs;

    public RateLimitConfig(long maxCapacity, long refillRatePerSecond, long windowDurationMs) {
        this.maxCapacity = maxCapacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.windowDurationMs = windowDurationMs;
    }

    // Getters
    public long getMaxCapacity() {
        return maxCapacity;
    }

    public long getRefillRatePerSecond() {
        return refillRatePerSecond;
    }

    public long getWindowDurationMs() {
        return windowDurationMs;
    }
}
