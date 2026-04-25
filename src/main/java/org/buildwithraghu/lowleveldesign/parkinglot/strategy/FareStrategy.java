package org.buildwithraghu.lowleveldesign.parkinglot.strategy;

import java.time.Duration;
import java.time.LocalDateTime;

public interface FareStrategy {
    double calculateFare(Duration duration, LocalDateTime entryTime);
}
