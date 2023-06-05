package com.food.ordering.system.valueobject;

import com.food.ordering.system.domain.valueobject.BaseID;

import java.util.UUID;

// the TrackingID will be returned to the client so he can track the order via the Get endpoint
public class TrackingID extends BaseID<UUID> {
    public TrackingID(UUID val) {
        super(val);
    }
}

