package org.buildwithraghu.lowleveldesign.ridesharingservice.strategy.pricing;

import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Location;
import org.buildwithraghu.lowleveldesign.ridesharingservice.enums.RideType;

public interface PricingStrategy {
    double calculateFare(Location pickup, Location dropoff, RideType rideType);
}
