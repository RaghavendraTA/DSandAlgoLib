package org.buildwithraghu.lowleveldesign.ridesharingservice.state;

import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Driver;
import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Trip;
import org.buildwithraghu.lowleveldesign.ridesharingservice.enums.TripStatus;

public class AssignedState implements TripState {
    @Override
    public void request(Trip trip) {
        System.out.println("Trip has alredy been requested and assigned");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
        System.out.println("Trip is already assigned. To re-assign cancel first");
    }

    @Override
    public void start(Trip trip) {
        trip.setStatus(TripStatus.IN_PROGRESS);
        trip.setState(new InProgressState());
    }

    @Override
    public void end(Trip trip) {
        System.out.println("Cannot end a trip that has not started yet");
    }
}
