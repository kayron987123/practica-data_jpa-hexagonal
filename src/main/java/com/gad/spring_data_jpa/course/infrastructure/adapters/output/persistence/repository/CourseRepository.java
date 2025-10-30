package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.repository;

import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findCourseEntityByName(String name);
}
