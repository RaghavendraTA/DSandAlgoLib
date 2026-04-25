package org.buildwithraghu.lowleveldesign.parkinglot;

import lombok.Setter;
import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingFloor;
import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingSpot;
import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingTicket;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.fee.FeeStrategy;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.fee.FlatRateStrategy;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.parking.BestFitStrategy;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.parking.ParkingStrategy;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeTickets;
    @Setter
    private FeeStrategy feeStrategy;
    @Setter
    private ParkingStrategy parkingStrategy;

    private ParkingLot() {
        this.feeStrategy = new FlatRateStrategy();
        this.parkingStrategy = new BestFitStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpot(floors, vehicle);

        if (availableSpot.isPresent()) {
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle, spot);
            activeTickets.put(vehicle.getLicenseNumber(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getLicenseNumber(), spot.getSpotId(), ticket.getTicketId());
            return Optional.of(ticket);
        }

        System.out.println("No available spot for " + vehicle.getLicenseNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);
        if (ticket == null) {
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setExitTimestamp();
        ticket.getSpot().unparkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        return Optional.of(parkingFee);
    }
}