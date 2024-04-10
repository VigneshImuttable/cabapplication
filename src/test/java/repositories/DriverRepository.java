package repositories;

import models.DriverPartner;
import org.springframework.stereotype.Component;

import java.sql.Driver;
import java.util.HashMap;
import java.util.Map;

@Component
public class DriverRepository {
    private  DriverRepository(){
    }

    private static final Map<Long, DriverPartner> drivers = new HashMap<>();
    private static Long idCounter = -1L;

    public static DriverPartner save(DriverPartner driverPartner){
        if (driverPartner.getId() == null) {
            driverPartner.setId(idCounter--);
        }
        drivers.put(driverPartner.getId(), driverPartner);
        return driverPartner;
    }

    public static DriverPartner findById(Long id) {
        return drivers.get(id);
    }


    public static Map<Long, DriverPartner> getAllDrivers(){
        return drivers;
    }


}






