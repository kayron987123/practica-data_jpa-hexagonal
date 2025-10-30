package com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence;

import com.gad.spring_data_jpa.course.application.ports.output.CoursePersistencePort;
import com.gad.spring_data_jpa.course.domain.exception.CourseNotFoundException;
import com.gad.spring_data_jpa.course.domain.model.Course;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.entity.CourseEntity;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.mapper.CourseMapper;
import com.gad.spring_data_jpa.course.infrastructure.adapters.output.persistence.repository.CourseRepository;
import com.gad.spring_data_jpa.department.domain.model.DepartmentNotFoundException;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.entity.DepartmentEntity;
import com.gad.spring_data_jpa.department.infrastructure.adapters.output.persistence.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CoursePersistencePort {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseMapper courseMapper;

    @Override
    public Course save(Course course) {
        CourseEntity courseEntity = courseRepository.save(courseMapper.courseToEntity(course));

        Course courseMapped = courseMapper.entityToCourse(courseEntity);

        DepartmentEntity department = departmentRepository.findById(courseEntity.getDepartment().getId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + courseEntity.getDepartment().getId()));

        courseMapped.withDepartmentName(department.getName());

        return courseMapped;
    }

    @Override
    public Optional<Course> findById(Long courseId) {
        CourseEntity courseEntityDb = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        DepartmentEntity department = departmentRepository.findById(courseEntityDb.getDepartment().getId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + courseEntityDb.getDepartment().getId()));

        Course courseMapped = courseMapper.entityToCourse(courseEntityDb);
        courseMapped.withDepartmentName(department.getName());

        return Optional.of(courseMapped);
    }

    @Override
    public List<Course> findAll() {
        List<CourseEntity> courseEntities = courseRepository.findAll();

        return courseEntities.stream().map(courseEntity -> {
            DepartmentEntity department = departmentRepository.findById(courseEntity.getDepartment().getId())
                    .orElseThrow(() -> new DepartmentNotFoundException("Department not found with id: " + courseEntity.getDepartment().getId()));

            Course courseMapped = courseMapper.entityToCourse(courseEntity);
            courseMapped.withDepartmentName(department.getName());

            return courseMapped;
        }).toList();
    }

    @Override
    public void deleteById(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
