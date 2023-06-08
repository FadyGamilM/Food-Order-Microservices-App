package com.food.ordering.system.dtos.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class DeliveryAddressDto {
    @NotNull
    private final String city;

    @NotNull
    private final String postalCode;

    @NotNull
    private final String street;

}
