package com.create.cabapplication.controllers;

import com.create.cabapplication.dtos.ConfirmTripDto;
import com.create.cabapplication.dtos.FindRideDto;
import com.create.cabapplication.dtos.FindRideResponseDto;
import com.create.cabapplication.dtos.TripConfirmationDto;
import com.create.cabapplication.models.LocationCoordinates;
import com.create.cabapplication.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.create.cabapplication.services.TripServices;
import com.create.cabapplication.utils.ConvertDtoToCoordinates;

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
        Map<String, LocationCoordinates> coordinates = ConvertDtoToCoordinates.convertDtoToCoordinates(findRideDto);
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
