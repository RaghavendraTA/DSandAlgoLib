package org.buildwithraghu.lowleveldesign.parkinglot.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class PeakHourStrategy implements FareStrategy {
    private final BaseFareStrategy baseFare = new BaseFareStrategy();
    private static final double PEAK_MULTIPLIER = 1.25;

    @Override
    public double calculateFare(Duration duration, LocalDateTime entryTime) {
        double fare = baseFare.calculateFare(duration, entryTime);
        if (isPeakHour(entryTime) || isPeakHour(entryTime.plus(duration))) {
            fare *= PEAK_MULTIPLIER;
        }
        return fare;
    }

    private boolean isPeakHour(LocalDateTime timestamp) {
        int hour = timestamp.getHour();
        return (hour >= 7 && hour < 10) || (hour >= 16 && hour < 19);
    }
}
