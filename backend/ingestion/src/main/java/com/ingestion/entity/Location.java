package com.ingestion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Location {

    @Column(name = "latitude")
    @JsonProperty("latitude")
    private double latitude;

    @Column(name = "longitude")
    @JsonProperty("longitude")
    private double longitude;

    @Column(name = "place")
    @JsonProperty("place")
    private String place;
}