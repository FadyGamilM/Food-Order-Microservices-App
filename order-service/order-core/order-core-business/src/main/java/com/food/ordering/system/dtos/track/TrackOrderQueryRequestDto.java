package com.food.ordering.system.dtos.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class TrackOrderQueryRequestDto {
    @NotNull
    private final UUID orderTrackingID;

}
