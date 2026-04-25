package org.buildwithraghu.lowleveldesign.ridesharingservice.entities;

import org.buildwithraghu.lowleveldesign.ridesharingservice.enums.RideType;

public class Vehicle {

    private final String licenseNumber;
    private final String model;
    private final RideType type;

    public Vehicle(String licenseNumber, String model, RideType type) {
        this.licenseNumber = licenseNumber;
        this.model = model;
        this.type = type;
    }

    public String getLicenseNumber() {
        return this.licenseNumber;
    }

    public String getModel() {
        return this.model;
    }

    public RideType getType() {
        return this.type;
    }
}
