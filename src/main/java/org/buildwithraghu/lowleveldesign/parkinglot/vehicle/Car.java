package org.buildwithraghu.lowleveldesign.parkinglot.vehicle;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleSize.MEDIUM);
    }
}