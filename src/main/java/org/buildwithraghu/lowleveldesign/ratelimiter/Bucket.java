package org.buildwithraghu.lowleveldesign.ratelimiter;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

public class Bucket {
    // tokens are represented as fractional via timestamp arithmetic; store last refill time and current tokens
    private long availableTokens;
    @Getter
    private long lastRefillTimeNanos;
    private long nanosPerToken;
    private long capacity;
    @Getter
    private final ReentrantLock lock = new ReentrantLock();

    Bucket(long nowNanos, long capacity, long nanosPerToken) {
        this.availableTokens = capacity;
        this.lastRefillTimeNanos = nowNanos;
        this.capacity = capacity;
        this.nanosPerToken = nanosPerToken;
    }

    boolean tryConsume(long nowNanos) {
        lock.lock();
        try {
            refillIfNeeded(nowNanos);
            if (availableTokens > 0) {
                availableTokens--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    long getLastRefillTimeNanos() {
        return lastRefillTimeNanos;
    }

    ReentrantLock getLock() {
        return lock;
    }

    void refillIfNeeded(long nowNanos) {
        if (nowNanos <= lastRefillTimeNanos) return;
        long elapsed = nowNanos - lastRefillTimeNanos;
        long tokensToAdd = elapsed / nanosPerToken;
        if (tokensToAdd > 0) {
            long newTokens = Math.min(this.capacity, availableTokens + tokensToAdd);
            availableTokens = newTokens;
            // advance lastRefillTimeNanos by tokensToAdd * nanosPerToken to avoid double counting
            lastRefillTimeNanos += tokensToAdd * nanosPerToken;
        }
    }
}
