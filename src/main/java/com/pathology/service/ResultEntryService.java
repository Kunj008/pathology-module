package com.pathology.service;

import com.pathology.dto.ResultEntryRequestDTO;
import com.pathology.dto.ResultEntryResponseDTO;
import com.pathology.entity.OrderStatus;
import com.pathology.entity.ResultEntry;
import com.pathology.entity.TestOrder;
import com.pathology.exception.ResourceNotFoundException;
import com.pathology.repository.ResultEntryRepository;
import com.pathology.repository.TestOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResultEntryService {

    private final ResultEntryRepository resultEntryRepository;
    private final TestOrderRepository testOrderRepository;

    @Transactional
    public ResultEntryResponseDTO enterResult(ResultEntryRequestDTO dto) {
        TestOrder order = testOrderRepository.findById(dto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + dto.getOrderId()));

        if (order.getStatus() == OrderStatus.COMPLETED) {
            throw new IllegalArgumentException("Result already entered for this order");
        }

        ResultEntry resultEntry = ResultEntry.builder()
                .testOrder(order)
                .resultValue(dto.getResultValue())
                .technicianNotes(dto.getTechnicianNotes())
                .completedDate(LocalDateTime.now())
                .build();

        ResultEntry savedResult = resultEntryRepository.save(resultEntry);

        order.setStatus(OrderStatus.COMPLETED);
        testOrderRepository.save(order);

        return mapToResponseDTO(savedResult);
    }

    public ResultEntryResponseDTO getResultByOrderId(Long orderId) {
        ResultEntry resultEntry = resultEntryRepository.findByTestOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found for order id: " + orderId));
        return mapToResponseDTO(resultEntry);
    }

    private ResultEntryResponseDTO mapToResponseDTO(ResultEntry result) {
        return ResultEntryResponseDTO.builder()
                .id(result.getId())
                .orderNumber(result.getTestOrder().getOrderNumber())
                .patientName(result.getTestOrder().getPatientName())
                .testName(result.getTestOrder().getTestMaster().getName())
                .resultValue(result.getResultValue())
                .technicianNotes(result.getTechnicianNotes())
                .completedDate(result.getCompletedDate())
                .build();
    }
}
