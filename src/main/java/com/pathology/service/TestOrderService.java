package com.pathology.service;

import com.pathology.dto.TestOrderRequestDTO;
import com.pathology.dto.TestOrderResponseDTO;
import com.pathology.entity.OrderStatus;
import com.pathology.entity.TestMaster;
import com.pathology.entity.TestOrder;
import com.pathology.exception.ResourceNotFoundException;
import com.pathology.repository.TestMasterRepository;
import com.pathology.repository.TestOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestOrderService {

    private final TestOrderRepository testOrderRepository;
    private final TestMasterRepository testMasterRepository;

    @Transactional
    public TestOrderResponseDTO createOrder(TestOrderRequestDTO dto) {
        TestMaster testMaster = testMasterRepository.findById(dto.getTestMasterId())
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + dto.getTestMasterId()));

        String orderNumber = generateOrderNumber();

        TestOrder order = TestOrder.builder()
                .patientName(dto.getPatientName())
                .phone(dto.getPhone())
                .testMaster(testMaster)
                .orderNumber(orderNumber)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .build();

        TestOrder savedOrder = testOrderRepository.save(order);
        return mapToResponseDTO(savedOrder);
    }

    public List<TestOrderResponseDTO> getTodayOrders() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return testOrderRepository.findByOrderDateBetween(startOfDay, endOfDay).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TestOrderResponseDTO> getAllOrders() {
        return testOrderRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public TestOrderResponseDTO getOrderById(Long id) {
        TestOrder order = testOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToResponseDTO(order);
    }

    private String generateOrderNumber() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "ORD-" + datePart + "-";
        long count = testOrderRepository.countByOrderNumberStartingWith(prefix);
        return prefix + String.format("%04d", count + 1);
    }

    private TestOrderResponseDTO mapToResponseDTO(TestOrder order) {
        return TestOrderResponseDTO.builder()
                .id(order.getId())
                .patientName(order.getPatientName())
                .phone(order.getPhone())
                .orderNumber(order.getOrderNumber())
                .testName(order.getTestMaster().getName())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .build();
    }
}
