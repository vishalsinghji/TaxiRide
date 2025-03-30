package com.ingestion.messaging;

import com.ingestion.entity.ImportantPlace;
import com.ingestion.entity.Ride;
import com.ingestion.repository.RideRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RideConsumer {

    private final RideRepository rideRepository;
    private final List<Ride> rideBuffer = new ArrayList<>();
    private static final int BATCH_SIZE = 100;

    public RideConsumer(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    @RabbitListener(queues = "rides.queue")
    public void receiveRide(Ride ride) {
        System.out.println("📩 Received ride: " + ride);
        if (ride.getImportantPlaces() != null) {
            for (ImportantPlace place : ride.getImportantPlaces()) {
                place.setRide(ride);
            }
        }

        rideBuffer.add(ride);

        if (rideBuffer.size() >= BATCH_SIZE) {
            rideRepository.saveAll(rideBuffer);
            System.out.println("Saved " + rideBuffer.size() + " rides with important places");
            rideBuffer.clear();
        }
    }
}
