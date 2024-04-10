package com.create.cabapplication.models;

import com.create.cabapplication.enums.TripStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trip {

   private Long driverId;
   private Long riderId;
   private LocationCoordinates source;
   private LocationCoordinates destination;
   private Double price;
   private  Long tripId;
   private TripStatus status;
}
