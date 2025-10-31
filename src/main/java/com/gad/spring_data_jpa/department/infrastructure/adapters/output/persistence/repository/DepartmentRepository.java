package com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.repository;

import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByNameContainingIgnoreCase(String name);
}
