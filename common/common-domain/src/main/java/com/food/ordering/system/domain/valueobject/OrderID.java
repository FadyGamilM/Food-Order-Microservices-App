package com.food.ordering.system.domain.valueobject;

import java.util.UUID;

public class OrderID extends BaseID<UUID> {
    public OrderID(UUID id) {
        super(id);
    }
}
