package com.taxiride.mapper;


import com.taxiride.dto.RideDTO;
import com.taxiride.entity.Ride;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RideMapper {
    public RideDTO toDTO(Ride ride) {
        return new RideDTO(
                ride.getStart().getPlace(),
                ride.getEnd().getPlace(),
                ride.getStartDate(),
                ride.getEndDate(),
                ride.getPrice()
        );
    }

    public List<RideDTO> toDTOList(List<Ride> rides) {
        return rides.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
