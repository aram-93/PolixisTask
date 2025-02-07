package com.polixisTask.task.service;

import com.polixisTask.task.model.enttity.Location;
import com.polixisTask.task.model.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public double calculateTotalDistance() {
        List<Location> locations = locationRepository.findAll();
        if (locations.size() < 2) return 0.0;

        double totalDistance = 0.0;
        for (int i = 1; i < locations.size(); i++) {
            totalDistance += haversine(
                    locations.get(i - 1).getLatitude(), locations.get(i - 1).getLongitude(),
                    locations.get(i).getLatitude(), locations.get(i).getLongitude()
            );
        }
        return totalDistance;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
