package com.gad.spring_data_jpa.course.application.ports.output;

import com.gad.spring_data_jpa.course.domain.model.CourseTeacherAssignment;

import java.util.List;

public interface CourseTeacherPersistencePort {
    CourseTeacherAssignment createCourseTeacherAssignment(CourseTeacherAssignment courseTeacherAssignment);
    List<CourseTeacherAssignment> findCoursesAssignedToTeachers(Long teacherId);
    List<CourseTeacherAssignment> findTeachersAssignedToCourse(Long courseId);
    void deleteCourseTeacherAssignment(Long courseId, Long teacherId);
}
