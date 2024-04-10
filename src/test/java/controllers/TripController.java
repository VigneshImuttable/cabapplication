package controllers;

import dtos.*;
import models.LocationCoordinates;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.TripServices;
import utils.ConvertDtoToCoordinates;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ride")
public class TripController {

    private final TripServices tripServices;

    public TripController(TripServices tripServices){
        this.tripServices = tripServices;
    }
    @PostMapping("/findDrivers")
    public List<DriverResponseDto> findRide(@RequestBody FindRideDto findRideDto){
        Map<String,LocationCoordinates> coordinates = ConvertDtoToCoordinates.convertDtoToCoordinates(findRideDto);
        LocationCoordinates source = coordinates.get("source");
        LocationCoordinates destination = coordinates.get("destination");
        Long riderId = findRideDto.getRiderId();
        String riderName = findRideDto.getName();

        return tripServices.findRideAvailable(source,destination,riderId);
    }

    @PostMapping("/chooseRide")
    public TripConfirmationDto confirmRide(@RequestBody SelectDriverDto selectDriverDto){



        return null;
    }

}
