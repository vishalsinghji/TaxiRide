package com.taxiride.service;

import com.taxiride.mapper.RideMapper;
import com.taxiride.dto.RideDTO;
import com.taxiride.dto.RideSummary;
import com.taxiride.entity.Ride;
import com.taxiride.repository.RideRepository;
import com.taxiride.utility.RideSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RideService {
    private final RideRepository rideRepository;
    private final RideMapper rideMapper;

    public RideService(RideRepository rideRepository, RideMapper rideMapper) {
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
    }

    public Page<RideDTO> getFilteredRides(Double minPrice, Double maxPrice, LocalDate startDate, LocalDate endDate,
                                          String startLocation, String endLocation, String sortBy, String sortDir, int page, int size) {
        Specification<Ride> spec = RideSpecification.filterRides(minPrice, maxPrice, startDate, endDate, startLocation, endLocation);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        var rides = rideRepository.findAll(spec, pageable);
        return rides.map(rideMapper::toDTO);
    }

    public RideSummary getRideSummary() {
        return rideRepository.getRideSummary();
    }
}
