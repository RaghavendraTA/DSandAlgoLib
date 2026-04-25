package org.buildwithraghu.lowleveldesign.ratelimiter.core;

public interface RateLimiterStrategy {

    boolean allowRequest(String key, long windowDurationMs);
    void reset(String key);
    void cleanup();
}
