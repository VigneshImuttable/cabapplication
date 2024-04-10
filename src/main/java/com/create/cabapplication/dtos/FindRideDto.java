package com.create.cabapplication.dtos;

import com.create.cabapplication.enums.TripStatus;
import com.create.cabapplication.models.LocationCoordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindRideDto {
    private Long riderId;
    private String name;
    private LocationCoordinates source;
    private LocationCoordinates destination;
}
