package com.food.ordering.system.ports.input.service;

import com.food.ordering.system.dtos.create.CreateOrderCommandRequestDto;
import com.food.ordering.system.dtos.create.CreateOrderCommandResponseDto;
import com.food.ordering.system.dtos.track.TrackOrderQueryRequestDto;
import com.food.ordering.system.dtos.track.TrackOrderQueryResponseDto;
import jakarta.validation.Valid;

// this input port will be used by the core-layer clients such as postman or any client app
public interface BusinessLogicService {
    CreateOrderCommandResponseDto createOrder(@Valid CreateOrderCommandRequestDto orderReqDto);

    TrackOrderQueryResponseDto trackOrderByID(@Valid TrackOrderQueryRequestDto trackReqDto);
}
