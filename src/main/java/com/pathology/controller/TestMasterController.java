package com.pathology.controller;

import com.pathology.dto.TestMasterRequestDTO;
import com.pathology.dto.TestMasterResponseDTO;
import com.pathology.service.TestMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@Tag(name = "Test Master", description = "Endpoints for managing available pathology tests")
public class TestMasterController {

    private final TestMasterService testMasterService;

    @PostMapping
    @Operation(summary = "Add a new pathology test", description = "Creates a new test in the master list")
    public ResponseEntity<TestMasterResponseDTO> createTest(@Valid @RequestBody TestMasterRequestDTO dto) {
        return new ResponseEntity<>(testMasterService.createTest(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all existing tests", description = "Fetches all tests available in the master list")
    public ResponseEntity<List<TestMasterResponseDTO>> getAllTests() {
        return ResponseEntity.ok(testMasterService.getAllTests());
    }

    @GetMapping("/search")
    @Operation(summary = "Search tests by name", description = "Provides a simple search by test name (partial match)")
    public ResponseEntity<List<TestMasterResponseDTO>> searchTests(@RequestParam String name) {
        return ResponseEntity.ok(testMasterService.searchTestsByName(name));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get test by ID", description = "Fetches a specific test by its unique ID")
    public ResponseEntity<TestMasterResponseDTO> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testMasterService.getTestById(id));
    }
}
