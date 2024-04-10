package com.create.cabapplication.dtos;

import com.create.cabapplication.models.LocationCoordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverResponseDto {
    private Long id;
    private String name;
    private LocationCoordinates currentLocationDriver;

    @Override
    public String toString() {
        return "DriverResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentLocationDriver=" + currentLocationDriver +
                '}';
    }
}
