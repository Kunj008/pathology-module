package com.pathology.controller;

import com.pathology.dto.ResultEntryRequestDTO;
import com.pathology.dto.ResultEntryResponseDTO;
import com.pathology.service.ResultEntryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
@Tag(name = "Result Entry", description = "Endpoints for entering and viewing test results")
public class ResultEntryController {

    private final ResultEntryService resultEntryService;

    @PostMapping
    @Operation(summary = "Enter test result", description = "Records the test result value and technician notes, marking the order as COMPLETED")
    public ResponseEntity<ResultEntryResponseDTO> enterResult(@Valid @RequestBody ResultEntryRequestDTO dto) {
        return new ResponseEntity<>(resultEntryService.enterResult(dto), HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "View completed result", description = "Fetches the result details for a specific test order")
    public ResponseEntity<ResultEntryResponseDTO> getResultByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(resultEntryService.getResultByOrderId(orderId));
    }
}
