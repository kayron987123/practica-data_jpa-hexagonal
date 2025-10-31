package com.gad.spring_data_jpa.department.application.application.ports.input.dto;

public record DepartmentDto(
        Long id,
        String name,
        String faculty
) {
}
