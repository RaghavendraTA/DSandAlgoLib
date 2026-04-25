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
        long now = System.nanoTime();
        BucketState state = buckets.compute(key, (k, existing) -> {
            if (existing == null) {
                // First request always allowed
                return new BucketState(1.0, now, true);
            }
            // Leak water based on elapsed time
            long elapsed = (now - existing.lastLeakTime) / 1_000_000; // ms
            double leaked = elapsed * leakRatePerMs;
            double water = Math.max(0, existing.waterLevel - leaked);

            if (water + 1 <= maxCapacity) {
                return new BucketState(water + 1, now, true);
            }
            // Bucket full — deny
            return new BucketState(water, now, false);
        });
        return state.allowed;
    }

    @Override
    public void reset(String key) {
        buckets.remove(key);
    }

    @Override
    public void cleanup() {
        long now = System.nanoTime();
        buckets.entrySet().removeIf(entry -> (now - entry.getValue().lastLeakTime) > 3600_000_000_000L);
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
