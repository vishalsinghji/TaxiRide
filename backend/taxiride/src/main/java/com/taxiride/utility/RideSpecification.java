package com.taxiride.utility;

import com.taxiride.entity.Ride;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RideSpecification {
    public static Specification<Ride> filterRides(Double minPrice, Double maxPrice, LocalDate startDate, LocalDate endDate, String startLocation, String endLocation) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }
            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate));
            }
            if (startLocation != null) {
                predicates.add(criteriaBuilder.like(root.get("start").get("place"), "%" + startLocation + "%"));
            }
            if (endLocation != null) {
                predicates.add(criteriaBuilder.like(root.get("end").get("place"), "%" + endLocation + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
