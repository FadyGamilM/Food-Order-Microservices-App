package com.food.ordering.system.valueobject;

import com.food.ordering.system.domain.valueobject.BaseID;
import com.food.ordering.system.entity.OrderItem;

// we need the orderItem to be unique in the context of the Order-Aggregate only, so we don't have to use UUID which makes the entity unqiue across the application context
public class OrderItemID extends BaseID<Long> {
    public OrderItemID(Long id) {
        super(id);
    }
}
