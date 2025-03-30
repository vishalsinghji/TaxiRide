package com.ingestion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "important_places")
public class ImportantPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude", nullable = false)
    @JsonProperty("latitude")
    private double latitude;

    @Column(name = "longitude", nullable = false)
    @JsonProperty("longitude")
    private double longitude;

    @Column(name = "place", nullable = false)
    @JsonProperty("place")
    private String place;

    @Setter
    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    @JsonBackReference
    private Ride ride;

}

