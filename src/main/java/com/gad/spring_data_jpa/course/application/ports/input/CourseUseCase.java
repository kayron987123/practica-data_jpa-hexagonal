package com.gad.spring_data_jpa.course.application.ports.input;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CreateCourseCommand;
import com.gad.spring_data_jpa.course.application.ports.input.dto.UpdateCourseCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CourseUseCase {
    CourseDto createCourse(CreateCourseCommand command);
    CourseDto getCourseById(Long courseId);
    CourseDto updateCourse(Long courseId, UpdateCourseCommand command);
    Page<CourseDto> getAllCourses(Pageable pageable);
    void deleteCourseById(Long courseId);
}
