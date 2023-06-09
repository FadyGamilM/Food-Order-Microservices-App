package com.food.ordering.system.dtos.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ResturantResponse {
    private final UUID ID;
    private final UUID sagaID;
    private final UUID orderID;
    private final UUID restaurantID;
    private final Instant createdAt;
    private final RestaurantApprovalStatus restaurantApprovalStatus;
}
 

