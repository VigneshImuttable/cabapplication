package repositories;

import models.Rider;
import models.Trip;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TripRepository {

    private static final Map<Long, Trip> trips = new HashMap<>();
    private static Long idCounter = 1L;

    public static Trip save(Trip trip) {
        if (trip.getTripId() == null) {
            trip.setTripId(idCounter++);
        }
        trips.put(trip.getTripId(), trip);
        return trip;
    }

    public static Trip findById(Long id) {
        return trips.get(id);
    }
}
