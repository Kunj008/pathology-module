package com.pathology.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestOrderRequestDTO {

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Test Master ID is required")
    private Long testMasterId;
}
