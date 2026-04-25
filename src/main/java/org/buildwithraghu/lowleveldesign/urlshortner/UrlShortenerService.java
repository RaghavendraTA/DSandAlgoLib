package org.buildwithraghu.lowleveldesign.urlshortner;

import org.buildwithraghu.lowleveldesign.urlshortner.enities.UrlRecord;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UrlShortenerService {
    private final ConcurrentHashMap<String, UrlRecord> codeToUrl = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> originalToCode = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ConcurrentHashMap<String, UrlRecord> getCodeToUrl() {
        return codeToUrl;
    }

    // Generate short URL automatically
    public String create(String originalUrl, Duration ttl) {
        String existing = originalToCode.get(originalUrl);
        if (existing != null) return Utils.BASE_HOST + existing;

        String code = Utils.encode(idGenerator.getAndIncrement());
        long expiryTime = (ttl == null) ? 0 : System.currentTimeMillis() + ttl.toMillis();

        UrlRecord record = new UrlRecord(originalUrl, expiryTime);
        codeToUrl.put(code, record);
        originalToCode.put(originalUrl, code);

        return Utils.BASE_HOST + code;
    }

    // Create with custom alias
    public String createCustom(String originalUrl, String alias, Duration ttl) {
        if (!Utils.isValidAlias(alias)) throw new IllegalArgumentException("Invalid alias");
        if (codeToUrl.containsKey(alias)) throw new IllegalArgumentException("Alias already taken");

        long expiryTime = (ttl == null) ? 0 : System.currentTimeMillis() + ttl.toMillis();
        UrlRecord record = new UrlRecord(originalUrl, expiryTime);

        codeToUrl.put(alias, record);
        originalToCode.put(originalUrl, alias);
        return Utils.BASE_HOST + alias;
    }

    // Redirect (lookup)
    public String get(String shortUrlOrCode) {
        String code = Utils.normalize(shortUrlOrCode);
        UrlRecord record = codeToUrl.get(code);
        if (record == null) return null;

        if (record.isExpired()) {
            codeToUrl.remove(code);
            return null;
        }
        record.incrementVisits();
        return record.getOriginalUrl();
    }

    // Optional: delete a short URL
    public boolean delete(String shortUrlOrCode) {
        String code = Utils.normalize(shortUrlOrCode);
        UrlRecord record = codeToUrl.remove(code);
        if (record != null) {
            originalToCode.remove(record.getOriginalUrl());
            return true;
        }
        return false;
    }
}

