package com.ingestion.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("api/v1/rides")
@Tag(name = "Rides API", description = "API for ingesting rides information to DB")
public interface RideControllerApi {

    @GetMapping("/read-file")
    @Operation(summary = "Ingest Json to DB", description = "To ingest Json Data to DB")
    public ResponseEntity<String> readFileAndSend();

}
