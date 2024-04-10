package com.create.cabapplication.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverPartner extends BaseModel {



    private String vehicleNumber;
    private Boolean active;
    private Boolean engaged;
    private LocationCoordinates currentLocation;


}
