package org.buildwithraghu.lowleveldesign.fooddeliveryservice.search;

import org.buildwithraghu.lowleveldesign.fooddeliveryservice.entity.Restaurant;

import java.util.List;

public interface RestaurantSearchStrategy {
    List<Restaurant> filter(List<Restaurant> allRestaurants);
}
