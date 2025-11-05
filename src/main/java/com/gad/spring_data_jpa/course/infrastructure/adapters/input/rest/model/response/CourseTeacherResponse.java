package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.response;

public record CourseTeacherResponse(
        Long courseId,
        Long teacherId,
        String courseName,
        String teacherName,
        Integer credits,
        String department
) {
}
