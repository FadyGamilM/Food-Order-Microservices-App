package com.food.ordering.system.ports.output.message.publisher.restaurant;

import com.food.ordering.system.domain.event.publisher.PublisherDomainEvent;
import com.food.ordering.system.event.OrderPaidEvent;

public interface OrderPaidMessagePublisher extends PublisherDomainEvent<OrderPaidEvent> {
}
