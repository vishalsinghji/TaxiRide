package com.taxiride.dto;



public interface RideSummary {
    Long getTotalRides();
    Double getAveragePrice();
    String getMostFrequentStartLocation();
    String getMostFrequentEndLocation();
}

