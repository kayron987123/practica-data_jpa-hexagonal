package com.gad.spring_data_jpa.course.application.ports.output;

import com.gad.spring_data_jpa.course.domain.model.Course;

import java.util.List;
import java.util.Optional;

public interface CoursePersistencePort {
    Course save(Course course);
    Optional<Course> findById(Long courseId);
    List<Course> findAll();
    void deleteById(Long courseId);
}
