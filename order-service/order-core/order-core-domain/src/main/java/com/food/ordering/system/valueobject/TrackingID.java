package com.food.ordering.system.valueobject;

import com.food.ordering.system.domain.valueobject.BaseID;

import java.util.UUID;

public class TrackingID extends BaseID<UUID> {
    public TrackingID(UUID val) {
        super(val);
    }
}
