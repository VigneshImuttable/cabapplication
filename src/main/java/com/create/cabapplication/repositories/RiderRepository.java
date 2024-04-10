package com.create.cabapplication.repositories;

import com.create.cabapplication.models.Rider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RiderRepository {
    private RiderRepository(){
    }
    private static final Map<Long, Rider> riders = new HashMap<>();
    private static Long idCounter = 1L;


    public static Rider save(Rider rider) {
        if (rider.getId() == null) {
            rider.setId(idCounter++);
        }
        riders.put(rider.getId(), rider);
        return rider;
    }

    public static Rider findById(Long id) {
        return riders.get(id);
    }


    }





