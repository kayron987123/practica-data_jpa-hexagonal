package com.gad.spring_data_jpa.student.infrastructure.output.persistence.repository;

import com.gad.spring_data_jpa.student.infrastructure.output.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByEmail(String email);
    Optional<StudentEntity> findByFullNameContainingIgnoreCase(String fullName);
}
