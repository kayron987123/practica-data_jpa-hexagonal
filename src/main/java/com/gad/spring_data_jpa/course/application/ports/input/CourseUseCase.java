package com.gad.spring_data_jpa.course.application.ports.input;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CreateCourseCommand;
import com.gad.spring_data_jpa.course.application.ports.input.dto.UpdateCourseCommand;

import java.util.List;

public interface CourseUseCase {
    CourseDto createCourse(CreateCourseCommand command);
    CourseDto getCourseById(Long courseId);
    CourseDto updateCourse(Long courseId, UpdateCourseCommand command);
    List<CourseDto> getAllCourses();
    void deleteCourseById(Long courseId);
}
