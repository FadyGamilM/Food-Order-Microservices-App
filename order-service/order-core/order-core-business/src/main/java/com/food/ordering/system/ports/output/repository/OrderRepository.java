package com.food.ordering.system.ports.output.repository;

import com.food.ordering.system.entity.Order;
import com.food.ordering.system.valueobject.TrackingID;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order saveOrder(Order order);

    // ! HE MADE THE trackingID as TrackingID not UUID
    Optional<Order> trackOrderByTrackingID(UUID trackingID);
}
