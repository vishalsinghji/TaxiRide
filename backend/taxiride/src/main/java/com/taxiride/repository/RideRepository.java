package com.taxiride.repository;


import com.taxiride.dto.RideSummary;
import com.taxiride.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long>, JpaSpecificationExecutor<Ride> {
    @Query(value = """
    SELECT COUNT(id) AS totalRides,
           AVG(price) AS averagePrice,
           (SELECT start_place FROM rides GROUP BY start_place ORDER BY COUNT(start_place) DESC LIMIT 1) AS mostFrequentStartLocation,
           (SELECT end_place FROM rides GROUP BY end_place ORDER BY COUNT(end_place) DESC LIMIT 1) AS mostFrequentEndLocation
    FROM rides
""", nativeQuery = true)
    RideSummary getRideSummary();
}
