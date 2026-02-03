package com.pathology.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "result_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Test Order reference is required")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_order_id", nullable = false, unique = true)
    private TestOrder testOrder;

    @NotBlank(message = "Result value is required")
    @Column(nullable = false)
    private String resultValue;

    private String technicianNotes;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime completedDate;

    @PrePersist
    protected void onCreate() {
        if (this.completedDate == null) {
            this.completedDate = LocalDateTime.now();
        }
    }
}
