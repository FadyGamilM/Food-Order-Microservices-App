package com.food.ordering.system.ports.implementation.input.service.handlers;

import com.food.ordering.system.dtos.track.TrackOrderQueryRequestDto;
import com.food.ordering.system.dtos.track.TrackOrderQueryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TrackOrderHandler {
    public TrackOrderQueryResponseDto trackOrder(TrackOrderQueryRequestDto trackDto) {
        return new TrackOrderQueryResponseDto();
    }
}
