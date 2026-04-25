package org.buildwithraghu.lowleveldesign.parkinglot.entities;

import org.buildwithraghu.lowleveldesign.parkinglot.vehicle.VehicleSize;

public class RegularSpot extends ParkingSpot {
    public RegularSpot(String id) {
        super(id, VehicleSize.MEDIUM);
    }
}
