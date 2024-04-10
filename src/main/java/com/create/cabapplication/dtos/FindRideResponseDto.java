package com.create.cabapplication.dtos;

import com.create.cabapplication.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindRideResponseDto {
    List<DriverResponseDto> driverResponseDtos;
    Long tripId;
    String message;
    TripStatus status;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FindRideResponseDto {")
                .append("tripId=").append(tripId)
                .append(", message='").append(message).append('\'')
                .append(", driverResponseDtos=").append(driverResponseDtos)
                .append(", tripStatus=").append(status)
                .append('}');
        return sb.toString();
    }
}
