package services;

import models.DriverPartner;
import models.LocationCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.DriverRepository;

import java.util.Optional;

@Service
public class DriverServices {

    public DriverPartner addDriver(String name, Integer age, String sex, String vehicleNumber, Boolean active, Boolean engaged, LocationCoordinates currentLocation) {

        DriverPartner driver;

        Optional<Integer> optionalAge = Optional.ofNullable(age);
        if (optionalAge.isPresent() && optionalAge.get() > 21 &&
                name != null && sex != null && vehicleNumber != null && active != null &&
                engaged != null && currentLocation.getX()!=null && currentLocation.getY()!=null) {
            // Create driver object
            driver = new DriverPartner();
            driver.setName(name);
            driver.setAge(age);
            driver.setSex(sex);
            driver.setVehicleNumber(vehicleNumber);
            driver.setActive(active);
            driver.setEngaged(engaged);
            driver.setCurrentLocation(currentLocation);
        }
        else {
            //Throw custom exception in next Build
            throw new IllegalArgumentException("All essential details of the driverPartner are required");
        }
        return DriverRepository.save(driver);
    }
}
