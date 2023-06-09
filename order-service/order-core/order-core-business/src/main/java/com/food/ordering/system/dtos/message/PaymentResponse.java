package com.food.ordering.system.dtos.message;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * this is the response returned from the payment service to the order service
 */
@AllArgsConstructor
@Getter
@Builder
/**
 *! IMPORTANT NOTE -> he made all ids as String not UUID ?
 */
public class PaymentResponse {
    @NotNull
    private final UUID ID;
    @NotNull
    private final UUID paymentID;
    @NotNull
    private final UUID orderID;
    @NotNull
    private final UUID customerID;
    @NotNull
    private final BigDecimal price;
    @NotNull
    private final UUID sagaID;
    @NotNull
    private final PaymentStatus paymentStatus;
    @NotNull
    private final Instant createdAt;
}
