package com.pathology.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMasterRequestDTO {

    @NotBlank(message = "Test name is required")
    private String name;

    @NotBlank(message = "Test code is required")
    private String code;

    @NotBlank(message = "Sample type is required")
    private String sampleType;

    @NotBlank(message = "Normal range is required")
    private String normalRange;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
}
