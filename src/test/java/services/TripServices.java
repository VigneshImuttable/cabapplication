package services;

import dtos.DriverResponseDto;
import dtos.TripConfirmationDto;
import lombok.Setter;
import models.DriverPartner;
import models.LocationCoordinates;
import org.springframework.stereotype.Service;
import repositories.DriverRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TripServices {

    private static final int EARTH_RADIUS_KM = 6371;
    private static final int Near_By_Distance_KM= 10;
    public List<DriverResponseDto> findRideAvailable(LocationCoordinates source, LocationCoordinates destination, Long riderId){

        List<DriverResponseDto> availableDriverPartner = new ArrayList<>();

        Map<Long,DriverPartner> allDrivingPartner = DriverRepository.getAllDrivers();
        for(Long id: allDrivingPartner.keySet()){
                DriverPartner curr = allDrivingPartner.get(id);
                if(!curr.getEngaged() && curr.getActive() && calculateDistance(source,curr.getCurrentLocation())>Near_By_Distance_KM){
                DriverResponseDto driverResponseDto = new DriverResponseDto();
                driverResponseDto.setName(curr.getName());
                driverResponseDto.setId(curr.getId());
                driverResponseDto.setCurrentLocationDriver(curr.getCurrentLocation());
                availableDriverPartner.add(driverResponseDto);
            }
           return availableDriverPartner;
        }
        return null;
    }

    public TripConfirmationDto confirmRide(Long driverId, Long riderId){
        return null;
    }

    public static double calculateDistance(LocationCoordinates source, LocationCoordinates destination) {
        double sourceLatitude = Math.toRadians(source.getX());
        double sourceLongitude = Math.toRadians(source.getY());
        double destLatitude = Math.toRadians(destination.getX());
        double destLongitude = Math.toRadians(destination.getY());

        double deltaLatitude = destLatitude - sourceLatitude;
        double deltaLongitude = destLongitude - sourceLongitude;

        double a = Math.pow(Math.sin(deltaLatitude / 2), 2) +
                Math.cos(sourceLatitude) * Math.cos(destLatitude) *
                        Math.pow(Math.sin(deltaLongitude / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c; // Distance in kilometers
    }







}
