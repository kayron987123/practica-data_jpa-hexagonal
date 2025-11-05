package com.gad.spring_data_jpa.student.application.ports.output;

import com.gad.spring_data_jpa.student.domain.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentPersistencePort {
    Student createStudent(Student student);
    Optional<Student> findById(Long studentId);
    Optional<Student> findByEmail(String email);
    Optional<Student> findByFullName(String fullName);
    void deleteById(Long studentId);
    Page<Student> findAll(Pageable pageable);
}
