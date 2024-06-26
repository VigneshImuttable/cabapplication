package com.create.cabapplication.dtos;

import com.create.cabapplication.models.LocationCoordinates;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverSignUpDto {
    private String name;
    private Integer age;
    private String sex;
    private String vehicleNumber;
    private Boolean active;
    private Boolean Engaged;
    private LocationCoordinates currentLocation;
}
