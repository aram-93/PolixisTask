package com.polixisTask.task.controller;

import com.polixisTask.task.kafka.LocationProducer;
import com.polixisTask.task.model.enttity.Location;
import com.polixisTask.task.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    private final LocationProducer locationProducer;
    private final LocationService locationService;

    public LocationController(LocationProducer locationProducer, LocationService locationService) {
        this.locationProducer = locationProducer;
        this.locationService = locationService;
    }

    @PostMapping("/send")
    public String sendLocation(@RequestBody Location location) {
        location.setTimestamp(LocalDateTime.now());
        locationProducer.sendLocationUpdate(location);
        return "Location sent!";
    }

    @GetMapping("/distance")
    public double getTotalDistance() {
        return locationService.calculateTotalDistance();
    }
}
