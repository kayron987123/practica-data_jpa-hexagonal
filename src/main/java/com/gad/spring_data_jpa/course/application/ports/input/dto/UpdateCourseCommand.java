package com.gad.spring_data_jpa.course.application.ports.input.dto;

public record UpdateCourseCommand(
        String name,
        Integer credits,
        String departmentName
) {
}
