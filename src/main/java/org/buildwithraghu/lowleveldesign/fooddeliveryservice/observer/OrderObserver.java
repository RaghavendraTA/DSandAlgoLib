package org.buildwithraghu.lowleveldesign.fooddeliveryservice.observer;

import org.buildwithraghu.lowleveldesign.fooddeliveryservice.order.Order;

public interface OrderObserver {
    void onUpdate(Order order);
}
