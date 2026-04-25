package org.buildwithraghu.lowleveldesign.bloomfilter;

import java.util.BitSet;
import java.util.List;

public class BloomFilter {

    private final BitSet bitSet;
    private final int bitSetSize;
    private final int numHashFunction;
    private final List<HashStrategy> hashStrategies;

    private BloomFilter(int bitSetSize, int numHashFunctions, List<HashStrategy> strategies) {
        this.bitSetSize = bitSetSize;
        this.numHashFunction = numHashFunctions;
        this.bitSet = new BitSet(bitSetSize);
        this.hashStrategies = strategies;
    }

    public void add(String item) {
        for(int i = 0; i < numHashFunction; i++) {
            long hash = hashStrategies.get(i).hash(item);
            int index = (int) (Math.abs(hash) % bitSetSize);
            bitSet.set(index);
        }
    }

    public boolean mightContain(String item) {
        for(int i = 0; i < numHashFunction; i++) {
            long hash = hashStrategies.get(i).hash(item);
            int index = (int) (Math.abs(hash) % bitSetSize);
            if (!bitSet.get(index)) {
                return false;
            }
        }
        return true;
    }

    public static class Builder {
        private int bitSetSize;
        private int numHashFunctions;
        private List<HashStrategy> strategies;

        public Builder withBitSetSize(int bitSetSize) {
            if (bitSetSize <= 0) {
                throw new IllegalArgumentException("BitSetSize must be positive");
            }
            this.bitSetSize = bitSetSize;
            return this;
        }

        public Builder withNumHashFunctions(int numHashFunctions) {
            if (numHashFunctions <= 0) {
                throw new IllegalArgumentException("Number of hash functions must be positive.");
            }
            this.numHashFunctions = numHashFunctions;
            return this;
        }

        public Builder withHashStrategies(List<HashStrategy> strategies) {
            if (strategies == null || strategies.isEmpty()) {
                throw new IllegalArgumentException("At least one hash strategy must be provided.");
            }
            this.strategies = strategies;
            return this;
        }

        public BloomFilter build() {
            if (bitSetSize == 0 || numHashFunctions == 0 || strategies == null) {
                throw new IllegalStateException("Must set bit set size, number of hash functions, and strategies.");
            }

            if (strategies.size() < numHashFunctions) {
                throw new IllegalStateException(
                        "The number of provided hash strategies (" + strategies.size() + ") " +
                                "must be at least equal to the number of hash functions required (" + numHashFunctions + ")."
                );
            }

            System.out.printf("Creating Bloom Filter with specified parameters:%n" +
                    "  - Bit set size (m): %d%n" +
                    "  - Hash functions (k): %d%n", bitSetSize, numHashFunctions);

            return new BloomFilter(bitSetSize, numHashFunctions, strategies);
        }
    }
}