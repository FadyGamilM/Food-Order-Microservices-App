package com.food.ordering.system.dtos.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class CreateOrderCommandRequestDto {
    private final UUID customerID;
    private final UUID restaurantID;
    private final List<OrderItemDto> orderItems;
    private final DeliveryAddressDto address;
    private final BigDecimal totalPrice;
}
