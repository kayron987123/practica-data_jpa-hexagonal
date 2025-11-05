package com.gad.spring_data_jpa.course.application.ports.input.dto;

import lombok.Builder;

@Builder
public record CourseTeacherDto(
        Long courseId,
        Long teacherId,
        String courseName,
        String teacherName,
        Integer credits,
        String department
) {
}
