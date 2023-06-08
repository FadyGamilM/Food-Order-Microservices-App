package com.food.ordering.system.dtos.create;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@AllArgsConstructor
@Builder
@Getter
public class CreateOrderCommandResponseDto {
    @NotNull
    private final UUID orderID;
    @NotNull
    private final OrderStatus status;
    @NotNull
    private final String msg; // !!! for what ?
}
