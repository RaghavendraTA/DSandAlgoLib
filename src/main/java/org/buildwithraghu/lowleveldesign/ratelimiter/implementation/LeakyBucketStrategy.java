package org.buildwithraghu.lowleveldesign.ratelimiter.implementation;

import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimitConfig;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimiterStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketStrategy implements RateLimiterStrategy {

    private final long maxCapacity;
    private final double leakRatePerMs;

    // key -> { waterLevel, lastLeakTime }
    private final Map<String, BucketState> buckets = new ConcurrentHashMap<>();

    public LeakyBucketStrategy(RateLimitConfig config) {
        this.maxCapacity = config.getMaxCapacity();
        this.leakRatePerMs = config.getRefillRatePerSecond() / 1000.0;
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

    private static class BucketState {
        double waterLevel;
        long lastLeakTime;
        boolean allowed;

        BucketState(double waterLevel, long lastLeakTime, boolean allowed) {
            this.waterLevel = waterLevel;
            this.lastLeakTime = lastLeakTime;
            this.allowed = allowed;
        }
    }
}
