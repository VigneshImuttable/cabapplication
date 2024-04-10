package dtos;

import lombok.Getter;
import lombok.Setter;
import models.LocationCoordinates;

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
