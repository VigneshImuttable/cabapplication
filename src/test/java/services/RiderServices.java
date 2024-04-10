package services;

import models.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.RiderRepository;

@Service
public class RiderServices {

    public Rider addRider(String name, Integer age, String sex) {
        Rider rider;

        if (name != null && age != null && sex != null) {
            rider = new Rider();
            rider.setName(name);
            rider.setAge(age);
            rider.setSex(sex);
        } else {
            throw new IllegalArgumentException("All essential details of the rider are required");
        }
        return RiderRepository.save(rider);
    }


}
