package com.create.cabapplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinishRideResponseDto {
    private Long tripId;
    Double price;
}
