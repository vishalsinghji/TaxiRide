package com.taxiride.controller;

import com.taxiride.api.RideRequestApi;
import com.taxiride.dto.RideDTO;
import com.taxiride.dto.RideSummary;
import com.taxiride.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
public class RideController implements RideRequestApi {

    private final RideService rideService;


    public ResponseEntity<Page<RideDTO>> getRides(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String startLocation,
            @RequestParam(required = false) String endLocation,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var rides = rideService.getFilteredRides(minPrice, maxPrice, startDate, endDate,
                startLocation, endLocation, sortBy, sortDir, page, size);
        return new ResponseEntity<>(rides,HttpStatus.OK);
    }



    public ResponseEntity<RideSummary> getRideSummary() {
        var rideSummary = rideService.getRideSummary();
        return new ResponseEntity<> (rideSummary, HttpStatus.OK);
    }
}

