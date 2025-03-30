package com.taxiride.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "start_latitude", nullable = false)),
            @AttributeOverride(name = "longitude", column = @Column(name = "start_longitude", nullable = false)),
            @AttributeOverride(name = "place", column = @Column(name = "start_place", nullable = false))
    })
    @JsonProperty("start")
    private Location start;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "end_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "end_longitude")),
            @AttributeOverride(name = "place", column = @Column(name = "end_place"))
    })
    private Location end;

    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImportantPlace> importantPlaces;

    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
}
