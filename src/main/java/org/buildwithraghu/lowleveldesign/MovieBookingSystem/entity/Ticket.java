package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Ticket {
    Screening screening;
    Seat seat;
    BigDecimal price;
}
