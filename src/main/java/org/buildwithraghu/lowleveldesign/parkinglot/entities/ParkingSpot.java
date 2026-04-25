package org.buildwithraghu.lowleveldesign.parkinglot.entities;

import lombok.Getter;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.Vehicle;
import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.VehicleSize;

public abstract class ParkingSpot {

    private final String id;
    private final VehicleSize size;
    private Vehicle currentVehicle;

    protected ParkingSpot(String id, VehicleSize size) {
        this.id = id;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public VehicleSize getSize() {
        return size;
    }

    public boolean isOccupied() {
        return currentVehicle != null;
    }

    public boolean canFit(Vehicle vehicle) {
        return !isOccupied() && size.ordinal() >= vehicle.getSize().ordinal();
    }

    public void assignVehicle(Vehicle vehicle) {
        if (!canFit(vehicle)) {
            throw new IllegalStateException("Vehicle " + vehicle + " cannot fit into spot " + id);
        }
        this.currentVehicle = vehicle;
    }

    public Vehicle removeVehicle() {
        Vehicle vehicle = currentVehicle;
        currentVehicle = null;
        return vehicle;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + id + ", size=" + size + ", occupied=" + isOccupied() + "]";
    }
}
