package com.pathology.repository;

import com.pathology.entity.TestMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMasterRepository extends JpaRepository<TestMaster, Long> {
    List<TestMaster> findByNameContainingIgnoreCase(String name);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
