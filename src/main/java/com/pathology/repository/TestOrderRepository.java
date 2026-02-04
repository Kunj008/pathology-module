package com.pathology.repository;

import com.pathology.entity.TestOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestOrderRepository extends JpaRepository<TestOrder, Long> {
    Optional<TestOrder> findByOrderNumber(String orderNumber);

    List<TestOrder> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);

    List<TestOrder> findByStatus(com.pathology.entity.OrderStatus status);

    @Query("SELECT COUNT(t) FROM TestOrder t WHERE t.orderNumber LIKE :prefix%")
    long countByOrderNumberStartingWith(@Param("prefix") String prefix);

    @Query("SELECT t FROM TestOrder t LEFT JOIN FETCH t.resultEntry LEFT JOIN FETCH t.testMaster")
    List<TestOrder> findAllWithDetails();
}
