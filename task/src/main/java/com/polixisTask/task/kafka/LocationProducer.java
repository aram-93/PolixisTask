package com.polixisTask.task.kafka;

import com.polixisTask.task.model.enttity.Location;
import com.polixisTask.task.model.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationProducer {
    private final KafkaTemplate<String, Location> kafkaTemplate;
    @Autowired
    LocationRepository locationRepository;

    public LocationProducer(KafkaTemplate<String, Location> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLocationUpdate(Location location) {
        kafkaTemplate.send("location_updates", location);
    }
}
