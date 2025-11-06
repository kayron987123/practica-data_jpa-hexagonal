package com.gad.spring_data_jpa.course.application.ports.input.dto;

import lombok.Builder;

@Builder
public record CoursesTeacherDataList(
        Long courseId,
        String courseName,
        Integer credits,
        String departmentName
) {
}
