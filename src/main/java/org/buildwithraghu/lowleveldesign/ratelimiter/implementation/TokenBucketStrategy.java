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
        return false;
    }

    @Override
    public void reset(String key) {
        
    }

    @Override
    public void cleanup() {
        
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
