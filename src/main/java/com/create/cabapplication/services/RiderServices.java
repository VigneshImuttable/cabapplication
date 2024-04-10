package com.create.cabapplication.services;

import com.create.cabapplication.models.Rider;
import org.springframework.stereotype.Service;
import com.create.cabapplication.repositories.RiderRepository;

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
