package com.create.cabapplication;

import com.create.cabapplication.controllers.DriverController;
import com.create.cabapplication.controllers.RiderController;
import com.create.cabapplication.controllers.TripController;
import com.create.cabapplication.dtos.*;
import com.create.cabapplication.models.LocationCoordinates;
import com.create.cabapplication.services.DriverServices;
import com.create.cabapplication.services.RiderServices;
import com.create.cabapplication.services.TripServices;
import com.create.cabapplication.strategies.DynamicPayStrategy;

public class DriverClass {

    public static void main(String[] Args){


        DriverController driverController = new DriverController(new DriverServices());
        RiderController riderController = new RiderController(new RiderServices());
        TripController tripController = new TripController(new TripServices(new DynamicPayStrategy()));

        DriverSignUpDto driver1 = new DriverSignUpDto();
        driver1.setName("John Doe");
        driver1.setAge(30);
        driver1.setSex("Male");
        driver1.setVehicleNumber("ABC123");
        driver1.setActive(true);
        driver1.setEngaged(false);
        LocationCoordinates driverLocation1 = new LocationCoordinates();
        driverLocation1.setX(40.7128);
        driverLocation1.setY(-74.0060);
        driver1.setCurrentLocation(driverLocation1);

        DriverSignUpDto driver2 = new DriverSignUpDto();
        driver2.setName("Alice Smith");
        driver2.setAge(35);
        driver2.setSex("Female");
        driver2.setVehicleNumber("XYZ789");
        driver2.setActive(true);
        driver2.setEngaged(false);
        LocationCoordinates driverLocation2 = new LocationCoordinates();
        driverLocation2.setX(34.0522);
        driverLocation2.setY(-118.2437);
        driver2.setCurrentLocation(driverLocation2);


        driverController.addDriver(driver1);
        driverController.addDriver(driver2);

        RiderSignUpDto rider1 = new RiderSignUpDto();
        rider1.setName("Emily Brown");
        rider1.setAge(28);
        rider1.setSex("Female");

        RiderSignUpDto rider2 = new RiderSignUpDto();
        rider2.setName("David Johnson");
        rider2.setAge(40);
        rider2.setSex("Male");

        riderController.addRider(rider1);
        riderController.addRider(rider2);

        FindRideDto findRideDto = new FindRideDto();
        findRideDto.setRiderId(-1L);
        findRideDto.setName("John Doe");
        LocationCoordinates source = new LocationCoordinates();
        source.setX(37.7749);
        source.setY(-122.4194);
        LocationCoordinates destination = new LocationCoordinates();
        destination.setX(40.7128);
        destination.setY(-74.0060);
        findRideDto.setSource(source);
        findRideDto.setDestination(destination);

        FindRideResponseDto findRideResponse = tripController.findRide(findRideDto).getBody();

        System.out.println(findRideResponse);

        assert findRideResponse != null;
        for (DriverResponseDto driver : findRideResponse.getDriverResponseDtos()) {
            System.out.println(driver.toString());
        }


        ConfirmTripDto confirmTripDto1 = new ConfirmTripDto();
        confirmTripDto1.setDriverId(-1L);
        confirmTripDto1.setDriverName("John Doe");
        confirmTripDto1.setTripId(1L);

        TripConfirmationResponseDto tripConfirmationResponse = tripController.confirmRide(confirmTripDto1).getBody();

        assert tripConfirmationResponse != null;

        System.out.println(tripConfirmationResponse.toString());


    }
}
