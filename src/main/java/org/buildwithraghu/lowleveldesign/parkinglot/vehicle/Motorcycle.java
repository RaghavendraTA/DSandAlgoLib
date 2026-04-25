package org.buildwithraghu.lowleveldesign.parkinglot.vehicle;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleSize.SMALL);
    }
}