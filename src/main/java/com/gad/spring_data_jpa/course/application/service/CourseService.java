package com.gad.spring_data_jpa.course.application.service;

import com.gad.spring_data_jpa.course.application.ports.input.CourseUseCase;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CreateCourseCommand;
import com.gad.spring_data_jpa.course.application.ports.input.dto.UpdateCourseCommand;
import com.gad.spring_data_jpa.course.application.ports.output.CoursePersistencePort;
import com.gad.spring_data_jpa.course.domain.exception.CourseNotFoundException;
import com.gad.spring_data_jpa.course.domain.model.Course;
import com.gad.spring_data_jpa.department.application.application.ports.output.DepartmentPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements CourseUseCase {
    private final CoursePersistencePort persistencePort;
    private final DepartmentPersistencePort departmentPersistencePort;

    @Override
    public CourseDto createCourse(CreateCourseCommand command) {
        Course course = Course.create(null, command.name(), command.credits(), command.departmentName());

        var courseSaved = persistencePort.save(course);
        var department = departmentPersistencePort.findByName(courseSaved.departmentName())
                .orElseThrow(() -> new RuntimeException("Department not found with name: " + courseSaved.departmentName()));

        return new CourseDto(courseSaved.id(), courseSaved.name(), courseSaved.credits(), courseSaved.departmentName(), department.faculty());
    }

    @Override
    public CourseDto getCourseById(Long courseId) {
        Course courseFound = persistencePort.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        var department = departmentPersistencePort.findByName(courseFound.departmentName())
                .orElseThrow(() -> new RuntimeException("Department not found with name: " + courseFound.departmentName()));

        return new CourseDto(courseFound.id(), courseFound.name(), courseFound.credits(), courseFound.departmentName(), department.faculty());
    }

    @Override
    public CourseDto updateCourse(Long courseId, UpdateCourseCommand command) {
        Course courseSaved = persistencePort.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        var courseToUpdate = courseSaved
                .withName(command.name())
                .withCredits(command.credits())
                .withDepartmentName(command.departmentName());

        var courseUpdated = persistencePort.save(courseToUpdate);

        var department = departmentPersistencePort.findByName(courseUpdated.departmentName())
                .orElseThrow(() -> new RuntimeException("Department not found with name: " + courseUpdated.departmentName()));

        return new CourseDto(courseUpdated.id(), courseUpdated.name(), courseUpdated.credits(), courseUpdated.departmentName(), department.faculty());
    }

    @Override
    public List<CourseDto> getAllCourses() {
        var courses = persistencePort.findAll();
        return courses.stream()
                .map(course -> {
                    var department = departmentPersistencePort.findByName(course.departmentName())
                            .orElseThrow(() -> new RuntimeException("Department not found with name: " + course.departmentName()));

                    return new CourseDto(course.id(), course.name(), course.credits(), course.departmentName(), department.faculty());
                })
                .toList();
    }

    @Override
    public void deleteCourseById(Long courseId) {
        var courseToDelete = persistencePort.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        persistencePort.deleteById(courseToDelete.id());
    }
}
