package org.buildwithraghu.lowleveldesign.parkinglot.strategy.parking;

import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingFloor;
import org.buildwithraghu.lowleveldesign.parkinglot.entities.ParkingSpot;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle);
}