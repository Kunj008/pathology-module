package com.pathology.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "test_master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Test name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Test code is required")
    @Column(nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Sample type is required")
    private String sampleType;

    @NotBlank(message = "Normal range is required")
    private String normalRange;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
}
