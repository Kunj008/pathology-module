package com.pathology.controller;

import com.pathology.dto.TestOrderRequestDTO;
import com.pathology.dto.TestOrderResponseDTO;
import com.pathology.service.TestOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Test Order", description = "Endpoints for creating and managing patient test orders")
public class TestOrderController {

    private final TestOrderService testOrderService;

    @PostMapping
    @Operation(summary = "Create a test order", description = "Creates a new test order for a patient and assigns a unique order number")
    public ResponseEntity<TestOrderResponseDTO> createOrder(@Valid @RequestBody TestOrderRequestDTO dto) {
        return new ResponseEntity<>(testOrderService.createOrder(dto), HttpStatus.CREATED);
    }

    @GetMapping("/today")
    @Operation(summary = "List today's orders", description = "Fetches all test orders created on the current date")
    public ResponseEntity<List<TestOrderResponseDTO>> getTodayOrders() {
        return ResponseEntity.ok(testOrderService.getTodayOrders());
    }

    @GetMapping
    @Operation(summary = "List all orders", description = "Fetches all test orders ever created")
    public ResponseEntity<List<TestOrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(testOrderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Fetches a specific test order by its unique ID")
    public ResponseEntity<TestOrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(testOrderService.getOrderById(id));
    }
}
