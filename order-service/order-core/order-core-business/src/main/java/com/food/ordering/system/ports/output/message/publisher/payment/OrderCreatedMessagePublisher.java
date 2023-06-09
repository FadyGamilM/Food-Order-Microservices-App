package com.food.ordering.system.ports.output.message.publisher.payment;

import com.food.ordering.system.domain.event.publisher.PublisherDomainEvent;
import com.food.ordering.system.event.OrderCreatedEvent;

public interface OrderCreatedMessagePublisher extends PublisherDomainEvent<OrderCreatedEvent> {
}
