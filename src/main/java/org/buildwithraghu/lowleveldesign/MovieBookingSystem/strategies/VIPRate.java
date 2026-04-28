package org.buildwithraghu.lowleveldesign.MovieBookingSystem.strategies;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VIPRate implements PricingStrategy {

    private final BigDecimal price;

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
