package org.buildwithraghu.designalgo;

import java.util.*;

// https://leetcode.com/problems/design-underground-system/
public class UndergroundSystem {

    // This solution is all O(1)
    static class CheckInData {
        int id;
        String station;
        int timestamp;
        CheckInData(int id, String station, int timestamp) {
            this.id = id;
            this.station = station;
            this.timestamp = timestamp;
        }
    }

    static class Stats {
        double count, total;
        Stats(double count, double total) {
            this.count = count;
            this.total = total;
        }
        double getAvg() {
            return total / count;
        }
    }

    private final Map<Integer, CheckInData> map;
    private final Map<String, Stats> avgData;

    public UndergroundSystem() {
        this.map = new HashMap<>();
        this.avgData = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        if (map.containsKey(id))
            return;
        map.put(id, new CheckInData(id, stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInData data = map.get(id);
        String key = data.station + "|" + stationName;
        double diff = t-data.timestamp;
        Stats s = avgData.getOrDefault(key, new Stats(0, 0));
        s.count++;
        s.total += diff;
        avgData.put(key, s);
        map.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "|" + endStation;
        if (!avgData.containsKey(key))
            return 0.0;
        return avgData.get(key).getAvg();
    }
}
