package org.buildwithraghu.lowleveldesign.urlshortner.enities;

import java.util.concurrent.atomic.AtomicLong;

// =============== Inner Record Class =================
public class UrlRecord {
    private final String originalUrl;
    private final long expiryTime;
    private final AtomicLong visitCount = new AtomicLong(0);

    public UrlRecord(String originalUrl, long expiryTime) {
        this.originalUrl = originalUrl;
        this.expiryTime = expiryTime;
    }

    public boolean isExpired() {
        return expiryTime > 0 && System.currentTimeMillis() > expiryTime;
    }

    public void incrementVisits() {
        visitCount.incrementAndGet();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public long getVisits() {
        return visitCount.get();
    }
}