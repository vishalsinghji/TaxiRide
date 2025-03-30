package com.taxiride.api;

import com.taxiride.dto.RideDTO;
import com.taxiride.dto.RideSummary;
import com.taxiride.entity.Ride;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequestMapping("api/v1/rides")
@Tag(name = "Rides API", description = "API for retrieving ride information")
public interface RideRequestApi {

    @Operation(summary = "Get paginated rides", description = "Retrieve paginated list of rides with filtering and sorting")
    @GetMapping
    ResponseEntity <Page<RideDTO>> getRides(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String startLocation,
            @RequestParam(required = false) String endLocation,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Get ride summary", description = "Retrieve aggregated ride data")
    @GetMapping("/summary")
    ResponseEntity <RideSummary> getRideSummary();
}
