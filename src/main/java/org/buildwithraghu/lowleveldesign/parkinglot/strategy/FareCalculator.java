package org.buildwithraghu.lowleveldesign.parkinglot.strategy;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;

public class FareCalculator {
    private final FareStrategy strategy;

    public FareCalculator(FareStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFare(Ticket ticket) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(ticket.getEntryTime(), now);
        return strategy.calculateFare(duration, ticket.getEntryTime());
    }
}
