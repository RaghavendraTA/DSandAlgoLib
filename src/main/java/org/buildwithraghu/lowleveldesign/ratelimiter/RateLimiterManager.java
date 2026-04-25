package org.buildwithraghu.lowleveldesign.ratelimiter;

import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimitConfig;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimiterFactory;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.RateLimiterStrategy;
import org.buildwithraghu.lowleveldesign.ratelimiter.core.StrategyType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RateLimiterManager {

    private final Map<String, RateLimiterStrategy> strategies = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r, "rate-limiter-cleanup-thread");
        t.setDaemon(true);
        return t;
    });

    public RateLimiterManager() {
        // Schedule cleanup every 1 minute
        scheduler.scheduleAtFixedRate(this::cleanup, 1, 1, TimeUnit.MINUTES);
    }

    public boolean isAllowed(String endpointId, String clientId, RateLimitConfig config, StrategyType type) {
        String strategyKey = endpointId + ":" + config.getMaxCapacity() + ":" + config.getWindowDurationMs();
        RateLimiterStrategy strategy = strategies.computeIfAbsent(strategyKey, key ->
                RateLimiterFactory.create(type, config));

        return strategy.allowRequest(clientId, config.getWindowDurationMs());
    }

    public void reset(String endpointId, String clientId) {
        strategies.values().forEach(strategy -> strategy.reset(clientId));
    }

    public void cleanup() {
        strategies.values().forEach(RateLimiterStrategy::cleanup);
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void resetAll() {
        strategies.clear();
    }
}
