package com.ingestion.controller;

import com.ingestion.api.RideControllerApi;
import com.ingestion.messaging.RideProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideController implements RideControllerApi {

    private final RideProducer rideProducer;

    public RideController(RideProducer rideProducer) {
        this.rideProducer = rideProducer;
    }

    public ResponseEntity<String> readFileAndSend() {
        rideProducer.sendRidesFromFile();
        return new  ResponseEntity <>("Rides from file sent to Queue ", HttpStatus.OK);
    }
}

