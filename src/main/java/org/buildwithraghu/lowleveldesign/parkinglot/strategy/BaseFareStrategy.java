package org.buildwithraghu.lowleveldesign.parkinglot.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class BaseFareStrategy implements FareStrategy {
    private static final double BASE_FARE = 5.00;
    private static final double HOURLY_RATE = 2.00;

    @Override
    public double calculateFare(Duration duration, LocalDateTime entryTime) {
        long minutes = duration.toMinutes();
        long hours = Math.max(1, (minutes + 59) / 60);
        return BASE_FARE + hours * HOURLY_RATE;
    }
}