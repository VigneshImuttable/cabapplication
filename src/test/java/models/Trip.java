package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trip {

    Long driverId;
    Long riderId;
    LocationCoordinates source;
    LocationCoordinates destination;
    Double price;
    Long tripId;

}
