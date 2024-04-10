package com.create.cabapplication.controllers;

import com.create.cabapplication.dtos.DriverLogOffDto;
import com.create.cabapplication.dtos.DriverSignUpDto;
import com.create.cabapplication.models.LocationCoordinates;
import com.create.cabapplication.models.DriverPartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.create.cabapplication.repositories.DriverRepository;
import com.create.cabapplication.services.DriverServices;
import com.create.cabapplication.utils.ConvertDtoToCoordinates;

@RestController
@RequestMapping("/driver")
public class DriverController {


   private final DriverServices driverServices;
    @Autowired
    public DriverController(DriverServices driverServices){
        this.driverServices = driverServices;
    }
    @PostMapping("/add")
    public DriverPartner addDriver(@RequestBody DriverSignUpDto driverSignUpDto){
        String name = driverSignUpDto.getName();
        Integer age = driverSignUpDto.getAge();
        String sex = driverSignUpDto.getSex();
        String vehicleNumber = driverSignUpDto.getVehicleNumber();
        Boolean active = driverSignUpDto.getActive();
        Boolean engaged = driverSignUpDto.getEngaged();
        LocationCoordinates currentLocation = ConvertDtoToCoordinates.convertDtoToCoordinate(driverSignUpDto);
        return driverServices.addDriver(name, age, sex, vehicleNumber, active, engaged, currentLocation);
    }
    @GetMapping("/{id}")
    public DriverPartner getDriver(@PathVariable("id") Long driverId) {
        return DriverRepository.findById(driverId);
    }

    @PostMapping("/logoff")
    public DriverPartner logOff(@RequestBody DriverLogOffDto logOffDto){
        return driverServices.logoff(logOffDto.getDriverId());
    }



}
