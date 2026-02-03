package com.pathology.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMasterResponseDTO {
    private Long id;
    private String name;
    private String code;
    private String sampleType;
    private String normalRange;
    private BigDecimal price;
}
