package org.buildwithraghu.lowleveldesign.ratelimiter.implementation;

import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimitConfig;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimiterStrategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketStrategy implements RateLimiterStrategy {

    private final long maxCapacity;
    private final double refillRatePerMs;

    // key -> { tokens, lastRefillTime }
    private final Map<String, BucketState> buckets = new ConcurrentHashMap<>();

    public TokenBucketStrategy(RateLimitConfig config) {
        this.maxCapacity = config.getMaxCapacity();
        this.refillRatePerMs = config.getRefillRatePerSecond() / 1000.0;
    }

    @Override
    public boolean allowRequest(String key, long windowDurationMs) {
        long now = System.nanoTime();
        BucketState state = buckets.compute(key, (k, existing) -> {
            if (existing == null) {
                return new BucketState((double) maxCapacity - 1.0, now, true);
            }
            long elapsed = (now - existing.lastRefillTime) / 1_000_000; // ms
            double newTokens = Math.min(maxCapacity, existing.tokens + elapsed * refillRatePerMs);
            if (newTokens >= 1.0) {
                return new BucketState(newTokens - 1.0, now, true);
            }
            // Not enough tokens — deny
            return new BucketState(existing.tokens, now, false);
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
        buckets.entrySet().removeIf(entry -> (now - entry.getValue().lastRefillTime) > 3600_000_000_000L);
    }

    private static class BucketState {
        double tokens;
        long lastRefillTime;
        boolean allowed;

        BucketState(double tokens, long lastRefillTime, boolean allowed) {
            this.tokens = tokens;
            this.lastRefillTime = lastRefillTime;
            this.allowed = allowed;
        }
    }
}
