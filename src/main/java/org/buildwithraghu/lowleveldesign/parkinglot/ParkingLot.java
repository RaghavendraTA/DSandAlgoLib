package org.buildwithraghu.lowleveldesign.parkinglot;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.Ticket;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;

import java.util.UUID;

public class ParkingLot {
    private final ParkingManager manager;

    public ParkingLot(ParkingManager manager) {
        this.manager = manager;
    }

    public Ticket enter(Vehicle vehicle) {
        return manager.park(vehicle);
    }

    public Ticket exit(UUID ticketId) {
        return manager.leave(ticketId);
    }

    public void printStatus() {
        manager.printStatus();
    }
}