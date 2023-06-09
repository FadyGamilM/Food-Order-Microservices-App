package com.food.ordering.system.ports.input.message.listeners.restaurant;

import com.food.ordering.system.dtos.message.ResturantResponse;
import jakarta.validation.Valid;

public interface ResturantApprovalMessageListener {
    void orderApproved(@Valid ResturantResponse resturantResponse);

    void orderRejected(@Valid ResturantResponse resturantResponse);
}
