package com.food.ordering.system.service;

import com.food.ordering.system.entity.Order;
import com.food.ordering.system.entity.Resturant;
import com.food.ordering.system.event.OrderCancelledEvent;
import com.food.ordering.system.event.OrderCreatedEvent;
import com.food.ordering.system.event.OrderPaidEvent;

import java.util.List;

/**
 * this domain service will be the interface for the (core-business) package to deal with our domain without exposing the domain entity's methods
 * each domain service method will return an event or nothing, and these events are fired via the business-layer but created at the domain-layer
 */

public interface OrderDomainService {
    OrderPaidEvent PayOrder(Order order);

    OrderCreatedEvent CreateAndInitiateOrder(Order order, Resturant resturant);

    void CancelOrder(Order order, List<String> failMsgs);

    OrderCancelledEvent CancelOrderPayment(Order order, List<String> failMsgs);

    void ApproveOrder(Order order);
}
