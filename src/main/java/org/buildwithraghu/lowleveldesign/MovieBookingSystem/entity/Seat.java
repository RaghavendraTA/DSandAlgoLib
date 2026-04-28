package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.strategies.PricingStrategy;

@Data
@AllArgsConstructor
public class Seat {

    String seatNumber;
    PricingStrategy pricingStrategy;
}
