package org.buildwithraghu.queue;

import java.util.*;
import java.util.stream.IntStream;

public class TopKElements {

    // Still O(n log(n))
    public int[] topKFrequent_SortedUsingMapValues(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return map.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    // Using bucket
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }

        // Step 2: Bucket sort by frequency
        int maxFreq = Collections.max(freqMap.values());
        List<ArrayList<Integer>> buckets = IntStream
                .range(0, maxFreq+1)
                .mapToObj(x -> new ArrayList<Integer>())
                .toList();

        freqMap.forEach((key, value) -> buckets.get(value).add(key));

        // Step 3: Collect results
        List<Integer> result = new ArrayList<>(k);
        for (int freq = maxFreq; freq > 0 && result.size() < k; freq--) {
            result.addAll(buckets.get(freq));
        }

        // Step 4: Trim to size k and convert
        return result.stream().limit(k).mapToInt(Integer::intValue).toArray();
    }
}
