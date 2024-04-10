package controllers;

import dtos.DriverSignUpDto;
import models.DriverPartner;
import models.LocationCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.DriverServices;
import utils.ConvertDtoToCoordinates;

@RestController
@RequestMapping("/driver")
public class DriverController {


   private final DriverServices driverServices;
    @Autowired
    public DriverController(DriverServices driverServices){
        this.driverServices = driverServices;
    }
    @RequestMapping("/add")
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




}
