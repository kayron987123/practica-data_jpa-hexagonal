package com.gad.spring_data_jpa.course.application.ports.output;

import com.gad.spring_data_jpa.course.domain.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CoursePersistencePort {
    Course save(Course course);
    Optional<Course> findById(Long courseId);
    Page<Course> findAll(Pageable pageable);
    void deleteById(Long courseId);
}
