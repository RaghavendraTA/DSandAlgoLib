package org.buildwithraghu.lowleveldesign.ratelimiter.core;

public enum StrategyType {
    TOKEN_BUCKET,
    FIXED_WINDOW,
    LEAKY_BUCKET,
    SLIDING_WINDOW
}
