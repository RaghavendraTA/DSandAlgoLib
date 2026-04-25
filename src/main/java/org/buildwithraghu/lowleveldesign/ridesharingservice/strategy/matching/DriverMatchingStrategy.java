package org.buildwithraghu.lowleveldesign.ridesharingservice.strategy.matching;

import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Driver;
import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Location;
import org.buildwithraghu.lowleveldesign.ridesharingservice.enums.RideType;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findDrivers(List<Driver> allDrivers, Location pickupLocation, RideType rideType);
}
