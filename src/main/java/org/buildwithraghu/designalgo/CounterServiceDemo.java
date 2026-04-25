package org.buildwithraghu.designalgo;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

class KeyEntity<T extends Comparable<T>> {
    private final T key;

    KeyEntity(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof KeyEntity<?> other) {
            return this.key.compareTo((T) other.key) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}

class Configuration {
    private final long interval = 300L;
    private static volatile Configuration INSTANCE;

    private Configuration() {}

    public long getInterval() {
        return interval;
    }

    public static Configuration getInstance() {
        if (INSTANCE == null) {
            synchronized (Configuration.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Configuration();
                }
            }
        }
        return INSTANCE;
    }
}

class CustomDataStructure<T extends Comparable<T>> {

    private final Map<T, Deque<Long>> ds = new ConcurrentHashMap<>();
    private final Configuration configuration = Configuration.getInstance();
    private final AtomicLong atomicLong = new AtomicLong(0);

    public void put(T key, long timestamp) {
        ds.computeIfAbsent(key, k -> new LinkedBlockingDeque<>()).addLast(timestamp);
        atomicLong.incrementAndGet();
    }

    public long getCount(T key) {
        Deque<Long> timestamps = ds.get(key);
        if (timestamps == null)
            throw new IllegalArgumentException("Key not found");

        cleanup();
        return timestamps.size();
    }

    public long getTotalCount() {
        cleanup();
        return atomicLong.get();
    }

    private void cleanup() {
        long cutoff = System.currentTimeMillis() - (configuration.getInterval() * 1000);
        for (Map.Entry<T, Deque<Long>> entry : ds.entrySet()) {
            Deque<Long> queue = entry.getValue();
            //synchronized (queue) {
                while (!queue.isEmpty() && queue.peekFirst() < cutoff) {
                    queue.pollFirst();
                    atomicLong.decrementAndGet();
                }
            //}
        }
    }
}

public class CounterServiceDemo {

    public static void main(String[] args) {
        CustomDataStructure<String> dataStructure = new CustomDataStructure<>();
        ExecutorService service = Executors.newFixedThreadPool(8);
        service.submit(() -> {
            for(int i = 400; i >= 100; i-=10) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                dataStructure.put("A", System.currentTimeMillis() - (i * 1000L));
            }
        });
        service.submit(() -> {
            for(int i = 300; i >= 0; i--) {
                dataStructure.put("B", System.currentTimeMillis() - (i * 1000L));
            }
        });
        // between 400-300 step of 10 whatever inserted is outside the scope so it should print 0, 10 times
        service.submit(() -> {
            for(int i = 0; i <= 300; i += 5) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(dataStructure.getCount("A"));
            }
        });
        /*
        dataStructure.put("A", System.currentTimeMillis() - (350 * 1000));
        dataStructure.put("A", System.currentTimeMillis() - 40L);
        dataStructure.put("A", System.currentTimeMillis() - 30L);
        System.out.println("Count(A): " + dataStructure.getCount("A"));
        System.out.println("Total Count: " + dataStructure.getTotalCount());
        */
        service.shutdown();
    }
}
