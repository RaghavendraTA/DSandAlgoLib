package org.buildwithraghu.lowleveldesign.parkinglot.entities;

import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.VehicleSize;

public class OversizedSpot extends ParkingSpot {
    public OversizedSpot(String id) {
        super(id, VehicleSize.LARGE);
    }
}