package org.buildwithraghu.lowleveldesign.ratelimiter.core;

import org.buildwithraghu.lowleveldesign.ratelimiter.implementation.FixedWindowStrategy;
import org.buildwithraghu.lowleveldesign.ratelimiter.implementation.LeakyBucketStrategy;
import org.buildwithraghu.lowleveldesign.ratelimiter.implementation.SlidingWindowStrategy;
import org.buildwithraghu.lowleveldesign.ratelimiter.implementation.TokenBucketStrategy;

public class RateLimiterFactory {

    public static RateLimiterStrategy create(StrategyType type, RateLimitConfig config) {
        switch (type) {
            case TOKEN_BUCKET:
                return new TokenBucketStrategy(config);
            case FIXED_WINDOW:
                return new FixedWindowStrategy(config);
            case LEAKY_BUCKET:
                return new LeakyBucketStrategy(config);
            case SLIDING_WINDOW:
                return new SlidingWindowStrategy(config);
            default:
                throw new IllegalArgumentException("Unsupported strategy type: " + type);
        }
    }
}
