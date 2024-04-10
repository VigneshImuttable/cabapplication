package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.DriverSignUpDto;
import dtos.FindRideDto;
import models.LocationCoordinates;

import java.util.HashMap;
import java.util.Map;

public class ConvertDtoToCoordinates {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, LocationCoordinates> convertDtoToCoordinates(FindRideDto findRideDto) {
        Map<String, LocationCoordinates> coordinatesMap = new HashMap<>();

        try {
            // Convert source and destination coordinates to LocationCoordinates objects
            LocationCoordinates source = objectMapper.convertValue(findRideDto.getSource(), LocationCoordinates.class);
            LocationCoordinates destination = objectMapper.convertValue(findRideDto.getDestination(), LocationCoordinates.class);

            // Put LocationCoordinates into the map
            coordinatesMap.put("source", source);
            coordinatesMap.put("destination", destination);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

        return coordinatesMap;
    }

    public static LocationCoordinates convertDtoToCoordinate(DriverSignUpDto driverSignUpDto){
        LocationCoordinates currentLocation = new LocationCoordinates();
        currentLocation.setX(driverSignUpDto.getCurrentLocation().getX());
        currentLocation.setY(driverSignUpDto.getCurrentLocation().getY());
        return currentLocation;
    }
}
