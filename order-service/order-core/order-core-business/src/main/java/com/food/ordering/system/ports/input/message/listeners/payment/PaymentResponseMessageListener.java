package com.food.ordering.system.ports.input.message.listeners.payment;

import com.food.ordering.system.dtos.message.PaymentResponse;
import jakarta.validation.Valid;

public interface PaymentResponseMessageListener {
    void paymentCompleted(@Valid PaymentResponse paymentResponse);

    void paymentCancelled(@Valid PaymentResponse paymentResponse);
}
