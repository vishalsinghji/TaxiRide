package com.taxiride.repository;

import com.taxiride.entity.ImportantPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportantPlaceRepository extends JpaRepository<ImportantPlace, Long> {
    List<ImportantPlace> findByRideId(Long rideId);
}
