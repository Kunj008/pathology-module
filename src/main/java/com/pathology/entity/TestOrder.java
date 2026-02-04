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
@Table(name = "test_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    @Column(nullable = false)
    private String patientName;

    @NotBlank(message = "Phone number is required")
    @Column(nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String orderNumber;

    @NotNull(message = "Test Master reference is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_master_id", nullable = false)
    private TestMaster testMaster;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @OneToOne(mappedBy = "testOrder")
    private ResultEntry resultEntry;

    @PrePersist
    protected void onCreate() {
        if (this.orderDate == null) {
            this.orderDate = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = OrderStatus.PENDING;
        }
    }
}
