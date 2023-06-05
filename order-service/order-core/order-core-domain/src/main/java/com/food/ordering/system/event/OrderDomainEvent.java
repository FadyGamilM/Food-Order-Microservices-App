package com.food.ordering.system.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderDomainEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderDomainEvent(Order order, ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
