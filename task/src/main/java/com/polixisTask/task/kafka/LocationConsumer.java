package com.polixisTask.task.kafka;

import com.polixisTask.task.model.enttity.Location;
import com.polixisTask.task.model.repository.LocationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LocationConsumer {
    private final LocationRepository locationRepository;

    public LocationConsumer(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @KafkaListener(topics = "location_updates", groupId = "location_group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Location location) {
        locationRepository.save(location);
        System.out.println("Saved location: " + location);
    }
}
