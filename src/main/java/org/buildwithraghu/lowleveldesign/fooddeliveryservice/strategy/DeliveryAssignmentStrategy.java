package org.buildwithraghu.lowleveldesign.fooddeliveryservice.strategy;

import org.buildwithraghu.lowleveldesign.fooddeliveryservice.entity.DeliveryAgent;
import org.buildwithraghu.lowleveldesign.fooddeliveryservice.order.Order;

import java.util.List;
import java.util.Optional;

public interface DeliveryAssignmentStrategy {
    Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents);
}
