package com.pathology.dto;

import com.pathology.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestOrderResponseDTO {
    private Long id;
    private String patientName;
    private String phone;
    private String orderNumber;
    private String testName;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private String resultValue;
}
