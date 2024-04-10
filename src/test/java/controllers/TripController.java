package controllers;

import dtos.*;
import models.LocationCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.TripServices;
import utils.ConvertDtoToCoordinates;

import java.util.Map;

@RestController
@RequestMapping("/ride")
public class TripController {

    private final TripServices tripServices;

    @Autowired
    public TripController(TripServices tripServices){
        this.tripServices = tripServices;
    }
    @PostMapping("/findDrivers")
    public ResponseEntity<FindRideResponseDto> findRide(@RequestBody FindRideDto findRideDto){
        Map<String,LocationCoordinates> coordinates = ConvertDtoToCoordinates.convertDtoToCoordinates(findRideDto);
        LocationCoordinates source = coordinates.get("source");
        LocationCoordinates destination = coordinates.get("destination");
        Long riderId = findRideDto.getRiderId();
        String riderName = findRideDto.getName();

        return tripServices.findRideAvailable(source,destination,riderId);
    }

    @PostMapping("/chooseRide")
    public ResponseEntity<TripConfirmationDto> confirmRide(@RequestBody ConfirmTripDto confirmTripDto){

        Long driverId = confirmTripDto.getDriverId();
        Long tripId = confirmTripDto.getTripId();

        return tripServices.confirmRide(driverId,tripId);
    }

}
