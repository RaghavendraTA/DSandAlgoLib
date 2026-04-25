package org.buildwithraghu.lowleveldesign.parkinglot.entities;

import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private final UUID ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double fare;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.ticketId = UUID.randomUUID();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getFare() {
        return fare;
    }

    public void close(double fare) {
        this.exitTime = LocalDateTime.now();
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Ticket[id=" + ticketId + ", vehicle=" + vehicle + ", spot=" + spot.getId() + ", entryTime=" + entryTime + ", exitTime=" + exitTime + ", fare=" + fare + "]";
    }
}
