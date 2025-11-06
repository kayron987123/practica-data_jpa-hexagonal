package com.gad.spring_data_jpa.course.application.ports.input;

import com.gad.spring_data_jpa.course.application.ports.input.dto.CourseTeacherDto;
import com.gad.spring_data_jpa.course.application.ports.input.dto.CoursesTeacherList;
import com.gad.spring_data_jpa.course.application.ports.input.dto.TeachersCourseList;


public interface CourseTeacherUseCase {
    CourseTeacherDto assignCourseToTeacher(Long teacherId, Long courseId);
    CourseTeacherDto updateCourseToTeacher(Long teacherId, Long courseId);
    void deleteCourseFromTeacher(Long idCourse, Long idTeacher);
    CoursesTeacherList getCoursesByTeacherId(Long teacherId);
    TeachersCourseList getTeachersByCourseId(Long courseId);
}
