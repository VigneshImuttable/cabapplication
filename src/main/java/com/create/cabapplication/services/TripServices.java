package com.create.cabapplication.services;

import com.create.cabapplication.dtos.FinishRideResponseDto;
import com.create.cabapplication.enums.TripStatus;
import com.create.cabapplication.models.LocationCoordinates;
import com.create.cabapplication.repositories.DriverRepository;
import com.create.cabapplication.dtos.DriverResponseDto;
import com.create.cabapplication.dtos.FindRideResponseDto;
import com.create.cabapplication.dtos.TripConfirmationResponseDto;
import com.create.cabapplication.models.DriverPartner;
import com.create.cabapplication.models.Trip;
import com.create.cabapplication.strategies.PayStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.create.cabapplication.repositories.TripRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TripServices {

    private static final int EARTH_RADIUS_KM = 6371;
    private static final int Near_By_Distance_KM= 10;

    @Autowired
    private PayStrategy payStrategy;

    @Autowired
    public TripServices(PayStrategy payStrategy){this.payStrategy=payStrategy;}


    public ResponseEntity<FindRideResponseDto> findRideAvailable(LocationCoordinates source, LocationCoordinates destination, Long riderId){

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
        }

        FindRideResponseDto responseDto;
        if(!availableDriverPartner.isEmpty()){
            Trip trip = createTrip(source,destination,riderId);
            String message = "Here are available drivers for you";
            responseDto = new FindRideResponseDto(availableDriverPartner, trip.getTripId(), message,TripStatus.REQUESTED);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }

        else{
            String message="There are No available drivers currently , Please retry";
            responseDto = new FindRideResponseDto();
            responseDto.setMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

    public ResponseEntity<TripConfirmationResponseDto> confirmRide(Long driverId, Long tripId) {
        // Check if the driver exists
        DriverPartner driverPartner = DriverRepository.findById(driverId);
        if (driverPartner == null) {
            throw new RuntimeException("No such driver found");
        }

        // Check if the driver is available and not engaged
        if (!driverPartner.getActive() || driverPartner.getEngaged()) {
            throw new RuntimeException("The driver is not available");
        }

        // Check if the trip exists
        Trip trip = TripRepository.findById(tripId);
        if (trip == null) {
            throw new RuntimeException("No such trip found");
        }

        // Update trip details and mark the driver as engaged
        trip.setDriverId(driverId);
        trip.setPrice(payStrategy.calculateAmount(calculateDistance(trip.getSource(), trip.getDestination())));
        trip.setStatus(trip.getStatus().tripStarted());
        driverPartner.setEngaged(true);

        //Synchronised block to perform the DB operations
        synchronized (this) {
            TripRepository.save(trip);
            DriverRepository.save(driverPartner);
        }

        TripConfirmationResponseDto tripConfirmationResponseDto = new TripConfirmationResponseDto(driverId, driverPartner.getName(),
                trip.getPrice(), "You have started your Trip");

        return ResponseEntity.status(HttpStatus.OK).body(tripConfirmationResponseDto);
    }

    public static Trip createTrip(LocationCoordinates source, LocationCoordinates destination, Long riderId){
        Trip trip = new Trip();
        trip.setRiderId(riderId);
        trip.setSource(source);
        trip.setDestination(destination);
        trip.setStatus(TripStatus.REQUESTED);
      return  TripRepository.save(trip);
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


    public FinishRideResponseDto finishRide(Long tripId) {
        Trip trip = TripRepository.findById(tripId);
        if (trip == null) {
            throw new RuntimeException("No such trip found");
        }
        trip.setStatus(TripStatus.COMPLETED);
      return new FinishRideResponseDto(tripId,trip.getPrice());
    }
}
