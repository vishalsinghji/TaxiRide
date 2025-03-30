package com.taxiride.dto;


import java.time.LocalDate;

public record RideDTO(
        String startPlace,
        String endPlace,
        LocalDate startDate,
        LocalDate endDate,
        double price
) {}
