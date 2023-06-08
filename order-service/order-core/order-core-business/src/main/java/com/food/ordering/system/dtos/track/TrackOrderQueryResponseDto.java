package com.food.ordering.system.dtos.track;

import com.food.ordering.system.domain.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class TrackOrderQueryResponseDto {
    @NotNull
    private final UUID orderTrackingID;
    @NotNull
    private final OrderStatus status;
    private final List<String> failMsgs;
}
