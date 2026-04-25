package org.buildwithraghu.lowleveldesign.parkinglot;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingSpot;
import org.buildwithraghu.lowleveldesign.parkinglot.entities.Ticket;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.FareCalculator;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;

import java.util.*;

public class ParkingManager {
    private final List<ParkingSpot> spots;
    private final Map<UUID, Ticket> activeTickets = new HashMap<>();
    private final FareCalculator fareCalculator;

    public ParkingManager(FareCalculator fareCalculator, List<ParkingSpot> spots) {
        this.fareCalculator = fareCalculator;
        this.spots = new ArrayList<>(spots);
    }

    public Ticket park(Vehicle vehicle) {
        Optional<ParkingSpot> spot = spots.stream()
                .filter(s -> s.canFit(vehicle))
                .sorted(Comparator.comparing(ParkingSpot::getSize))
                .findFirst();

        ParkingSpot foundSpot = spot.orElseThrow(() -> new IllegalStateException("No available spot for " + vehicle));
        foundSpot.assignVehicle(vehicle);
        Ticket ticket = new Ticket(vehicle, foundSpot);
        activeTickets.put(ticket.getTicketId(), ticket);
        System.out.println("Parked " + vehicle + " in " + foundSpot.getId());
        return ticket;
    }

    public Ticket leave(UUID ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket " + ticketId + " is invalid or already closed.");
        }
        ParkingSpot spot = ticket.getSpot();
        spot.removeVehicle();
        double fare = fareCalculator.calculateFare(ticket);
        ticket.close(fare);
        System.out.println("Vehicle " + ticket.getVehicle().getLicensePlate() + " left spot " + spot.getId() + ". Fare=" + fare);
        return ticket;
    }

    public List<ParkingSpot> getAvailableSpots() {
        List<ParkingSpot> available = new ArrayList<>();
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                available.add(spot);
            }
        }
        return available;
    }

    public void printStatus() {
        System.out.println("--- Parking Status ---");
        for (ParkingSpot spot : spots) {
            System.out.println(spot + (spot.isOccupied() ? " holds " + spot.getCurrentVehicle() : " is free"));
        }
        System.out.println("Active tickets: " + activeTickets.size());
        System.out.println("----------------------");
    }
}
