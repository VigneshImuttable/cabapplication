package dtos;

import lombok.Getter;
import lombok.Setter;
import models.LocationCoordinates;

@Getter
@Setter
public class FindRideDto {
    private Long riderId;
    private String name;
    private LocationCoordinates source;
    private LocationCoordinates destination;
}
