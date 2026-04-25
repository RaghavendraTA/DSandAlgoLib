package org.buildwithraghu.mapsandsets;

import java.util.*;
import java.util.concurrent.*;

public class DifferentMaps {

    private static final Random random = new Random();

    // Not-Thread safe but O(1)
    public static void HashMapType(List<Map.Entry<Integer, Integer>> entries) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        entries.forEach((e) -> {
            hmap.putIfAbsent(e.getKey(), e.getValue());
        });
    }

    // Not-Thread safe but O(1)
    public static void LinkedHashMapType() {
        LinkedHashMap<Integer, Integer> lhmap = new LinkedHashMap<>();
        lhmap.putLast(1, 10);
        lhmap.putFirst(2, 20);
        lhmap.putFirst(1, 30);
        for (Map.Entry<Integer, Integer> me : lhmap.entrySet()) {
            System.out.println(me.getKey() + " = " + me.getValue());
        }
    }

    // Not-Thread safe but O(logn), keeps sorted & good for range queries
    public static void SortedMapType() {
        // Red-Black Tree
        SortedMap<Integer, Integer> tmap = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            tmap.put(random.nextInt(0, 100), i);
        }
        for (Map.Entry<Integer, Integer> me : tmap.entrySet()) {
            System.out.println(me.getKey() + " = " + me.getValue());
        }
    }

    // Thread-safe as its synchronized but slower than ConcurrentHashMap
    @Deprecated
    public static void HashtableType() {
        Map<Integer, Integer> hashtable = new Hashtable<>();
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            executor.submit(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                        int x = random.nextInt(0, 100);
                        System.out.println("Put = [" + i + ", " + x + "]");
                        hashtable.put(i, x);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            executor.submit(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.println("Get = [" + i + ", " + (hashtable.get(i)) + "]");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    // Thread-safe Concurrent
    public static void ConcurrentSkipListMapType() {
        ConcurrentSkipListMap<Integer, Integer> csmap = new ConcurrentSkipListMap<>();
        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            executor.submit(() -> {
                for (int i = 10; i >= 0; i--) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(300);
                        int x = random.nextInt(0, 10);
                        System.out.println("Put = [" + i + ", " + x + "]");
                        csmap.put(i, x);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            executor.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                    for (Map.Entry<Integer, Integer> me : csmap.entrySet()) {
                        TimeUnit.MILLISECONDS.sleep(200);
                        System.out.println("Get = [" + me.getKey() + ", " + me.getValue() + "]");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    // Thread Safe and O(1)
    public static void ConcurrentMapType() {
        ConcurrentMap<Integer, Integer> cmap = new ConcurrentHashMap<>();

        Callable<Object> task1 = Executors.callable(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.println("Put = [" + i + ", " + (i * 2) + "]");
                    cmap.putIfAbsent(i, i * 2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Callable<Object> task2 = Executors.callable(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Get = [" + i + ", " + (cmap.get(i)) + "]");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try (ExecutorService executor = Executors.newFixedThreadPool(5)) {
            executor.invokeAll(List.of(task1, task2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("From main");
    }

    // Something New type of Map
    enum Days {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    // Not-thread safe but superfast
    public static void enumMapType() {
        EnumMap<Days, Integer> enumMap = new EnumMap<>(Days.class);
        for(var d: Days.values()) {
            enumMap.put(d, random.nextInt(0, 8));
        }
        for(Map.Entry<Days, Integer> me: enumMap.entrySet()) {
            System.out.println(me.getKey() + " <-> " + me.getValue());
        }
    }
}
