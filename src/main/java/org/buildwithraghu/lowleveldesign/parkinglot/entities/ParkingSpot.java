package org.buildwithraghu.lowleveldesign.parkinglot.entities;

import lombok.Getter;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.VehicleSize;

public class ParkingSpot {

    @Getter
    private final String spotId;
    @Getter
    private boolean isOccupied;
    @Getter
    private final VehicleSize spotSize;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotId = spotId;
        this.spotSize = spotSize;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public synchronized boolean isAvailable() {
        return !isOccupied;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

     public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public String getSpotId() {
        return spotId;
    }

   public VehicleSize getSpotSize() {
        return spotSize;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

   public boolean canFitVehicle(Vehicle vehicle) {
        if (isOccupied) return false;

        return switch (vehicle.getSize()) {
            case SMALL -> spotSize == VehicleSize.SMALL;
            case MEDIUM -> spotSize == VehicleSize.MEDIUM || spotSize == VehicleSize.LARGE;
            case LARGE -> spotSize == VehicleSize.LARGE;
            default -> false;
        };
    }
}
