package org.buildwithraghu.lowleveldesign.bloomfilter;

import java.nio.charset.StandardCharsets;

interface HashStrategy {
    long hash(String data);
}

public class FNV1aHashStrategy implements HashStrategy  {

    private static final long FNV_PRIME = 0x100000001b3L;
    private static final long FNV_OFFSET_BASIS = 0xcbf29ce484222325L;

    @Override
    public long hash(String data) {
        long hash = FNV_OFFSET_BASIS;
        for (byte b: data.getBytes(StandardCharsets.UTF_8)) {
            hash ^= b;
            hash *= FNV_PRIME;
        }
        return hash;
    }
}

class DJB2HashStrategy implements HashStrategy {

    @Override
    public long hash(String data) {
        long hash = 5381L;
        for (byte b : data.getBytes(StandardCharsets.UTF_8)) {
            // hash = hash * 33 + c
            hash = ((hash << 5) + hash) + b;
        }
        return hash;
    }
}
