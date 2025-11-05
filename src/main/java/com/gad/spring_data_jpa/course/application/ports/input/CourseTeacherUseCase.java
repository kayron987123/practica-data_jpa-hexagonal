package com.gad.spring_data_jpa.course.application.ports.input;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;

import java.util.List;

public interface CourseTeacherUseCase {
    CourseTeacherDto assignCourseToTeacher(Long teacherId, Long courseId);
    CourseTeacherDto updateCourseToTeacher(Long teacherId, Long courseId);
    void deleteCourseFromTeacher(Long idCourse, Long idTeacher);
    List<CourseTeacherDto> getCoursesByTeacherId(Long teacherId);
    List<CourseTeacherDto> getTeachersByCourseId(Long courseId);
}
