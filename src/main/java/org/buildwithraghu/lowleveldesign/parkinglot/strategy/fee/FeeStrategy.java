package org.buildwithraghu.lowleveldesign.parkinglot.strategy.fee;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
