package com.pathology.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntryResponseDTO {
    private Long id;
    private String orderNumber;
    private String patientName;
    private String testName;
    private String resultValue;
    private String technicianNotes;
    private LocalDateTime completedDate;
}
