package org.buildwithraghu.lowleveldesign.bloomfilter;

public class HashStrategyFactory {

    public static HashStrategy create(HashType type) {
        switch (type) {
            case DJB2:
                return new DJB2HashStrategy();
            case FNV1A:
                return new FNV1aHashStrategy();
            default:
                throw new IllegalArgumentException("Unsupported hash type: " + type);
        }
    }
}
