package com.ingestion.repository;


import com.ingestion.entity.ImportantPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportantPlaceRepository extends JpaRepository<ImportantPlace, Long> {
}
