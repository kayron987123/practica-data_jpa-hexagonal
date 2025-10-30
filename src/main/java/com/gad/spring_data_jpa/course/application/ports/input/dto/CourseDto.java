package com.gad.spring_data_jpa.course.application.ports.input.dto;

public record CourseDto(
        Long id,
        String name,
        Integer credits,
        String departmentName,
        String departmentFaculty
) {
}
