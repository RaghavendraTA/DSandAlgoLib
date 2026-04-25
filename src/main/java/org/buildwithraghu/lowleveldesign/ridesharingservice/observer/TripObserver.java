package org.buildwithraghu.lowleveldesign.ridesharingservice.observer;

import org.buildwithraghu.lowleveldesign.ridesharingservice.entities.Trip;

public interface TripObserver {
    void onUpdate(Trip trip);
}