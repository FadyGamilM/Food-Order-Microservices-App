package com.food.ordering.system.ports.implementation.input.service;

import com.food.ordering.system.dtos.create.CreateOrderCommandRequestDto;
import com.food.ordering.system.dtos.create.CreateOrderCommandResponseDto;
import com.food.ordering.system.dtos.track.TrackOrderQueryRequestDto;
import com.food.ordering.system.dtos.track.TrackOrderQueryResponseDto;
import com.food.ordering.system.ports.implementation.input.service.handlers.CreateOrderHandler;
import com.food.ordering.system.ports.implementation.input.service.handlers.TrackOrderHandler;
import com.food.ordering.system.ports.input.service.BusinessLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
/**
 * !!! => i made the implementation details as package private because i don't want to expose the details, i want to just expose the abstractions
 */
class BusinessLogicServiceImp implements BusinessLogicService {
    private final CreateOrderHandler _createOrderHandler;
    private final TrackOrderHandler _trackOrderHandler;

    // * constructor injection
    BusinessLogicServiceImp(CreateOrderHandler createOrderHandler, TrackOrderHandler trackOrderHandler) {
        _createOrderHandler = createOrderHandler;
        _trackOrderHandler = trackOrderHandler;
    }


    @Override
    public CreateOrderCommandResponseDto createOrder(CreateOrderCommandRequestDto orderReqDto) {
        return _createOrderHandler.createOrder(orderReqDto);
    }

    @Override
    public TrackOrderQueryResponseDto trackOrderByID(TrackOrderQueryRequestDto trackReqDto) {
        return _trackOrderHandler.trackOrder(trackReqDto);
    }
}
