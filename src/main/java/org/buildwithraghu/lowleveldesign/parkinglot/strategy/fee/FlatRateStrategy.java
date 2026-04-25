package org.buildwithraghu.lowleveldesign.parkinglot.strategy.fee;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingTicket;

public class FlatRateStrategy implements FeeStrategy {

    private static final double RATE_PER_HOUR = 10.0;

    @Override
    public double calculateFee(ParkingTicket ticket) {
        long duration = ticket.getExitTimestamp() - ticket.getEntryTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * RATE_PER_HOUR;
    }
}
