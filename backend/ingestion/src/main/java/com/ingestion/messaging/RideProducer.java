package com.ingestion.messaging;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingestion.entity.Ride;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Service
public class RideProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spring.rabbitmq.exchange.rideExchange}")
    private String rideExchange;

    @Value("${spring.rabbitmq.routing.rideRoutingKey}")
    private String rideRoutingKey;

    public RideProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendRidesFromFile() {
        try {
            File file = ResourceUtils.getFile("src/main/resources/taxi_records.json");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();
            List<Ride> rides = objectMapper.readValue(jsonString.toString(), new TypeReference<List<Ride>>() {});

            int batchSize = 100;
            for (int i = 0; i < rides.size(); i++) {
                Ride ride = rides.get(i);
                rabbitTemplate.convertAndSend(rideExchange,rideRoutingKey, ride);

                if (i % batchSize == 0) {
                    System.out.println("Sent " + (i + 1) + " rides to queue");
                }
            }

            System.out.println("Finished sending all rides to RabbitMQ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
