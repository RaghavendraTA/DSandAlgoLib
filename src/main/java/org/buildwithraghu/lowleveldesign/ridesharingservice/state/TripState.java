package org.buildwithraghu.lowleveldesign.ridesharingservice.state;

import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Driver;
import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Trip;

public interface TripState {
    void request(Trip trip);
    void assign(Trip trip, Driver driver);
    void start(Trip trip);
    void end(Trip trip);
}
