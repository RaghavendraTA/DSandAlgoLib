package org.buildwithraghu.lowleveldesign.parkinglot.entities;

import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.VehicleSize;

public class CompactSpot extends ParkingSpot {
    public CompactSpot(String id) {
        super(id, VehicleSize.SMALL);
    }
}