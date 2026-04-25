package org.buildwithraghu.lowleveldesign.parkinglot;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.*;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.FareCalculator;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.FareStrategy;
import org.buildwithraghu.lowleveldesign.parkinglot.strategy.PeakHourStrategy;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotDemo {

    static void sleepMinutes(int minutes) {
        try {
            Thread.sleep(minutes * 10L);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        FareStrategy fareStrategy = new PeakHourStrategy();
        FareCalculator fareCalculator = new FareCalculator(fareStrategy);

        List<ParkingSpot> spots = new ArrayList<>();
        spots.add(new CompactSpot("C1"));
        spots.add(new CompactSpot("C2"));
        spots.add(new RegularSpot("R1"));
        spots.add(new RegularSpot("R2"));
        spots.add(new OversizedSpot("O1"));

        ParkingManager manager = new ParkingManager(fareCalculator, spots);
        ParkingLot parkingLot = new ParkingLot(manager);

        Vehicle bike = new Motorcycle("MC-101");
        Vehicle car = new Car("CAR-202");
        Vehicle truck = new Truck("TRK-303");

        Ticket ticket1 = parkingLot.enter(bike);
        Ticket ticket2 = parkingLot.enter(car);
        Ticket ticket3 = parkingLot.enter(truck);

        parkingLot.printStatus();

        sleepMinutes(90);

        parkingLot.exit(ticket2.getTicketId());

        parkingLot.printStatus();
    }
}
