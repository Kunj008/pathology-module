package com.pathology.repository;

import com.pathology.entity.ResultEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultEntryRepository extends JpaRepository<ResultEntry, Long> {
    Optional<ResultEntry> findByTestOrderId(Long testOrderId);
}
