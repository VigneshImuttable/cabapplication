package dtos;

import lombok.Getter;
import lombok.Setter;
import models.LocationCoordinates;

@Getter
@Setter
public class DriverResponseDto {
    private long id;
    private String name;
    private LocationCoordinates currentLocationDriver;
}
