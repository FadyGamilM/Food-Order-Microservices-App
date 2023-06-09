package com.food.ordering.system.dtos.message;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ResturantResponse {

    @NotNull
    private final UUID ID;
    @NotNull
    private final UUID sagaID;
    @NotNull
    private final UUID orderID;
    @NotNull
    private final UUID restaurantID;
    @NotNull
    private final Instant createdAt;
    @NotNull
    private final RestaurantApprovalStatus restaurantApprovalStatus;
}
 

