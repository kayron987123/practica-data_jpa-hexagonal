package com.gad.spring_data_jpa.department.application.application.ports.input.dto;

public record CreateDepartmentCommand(
        String name,
        String faculty
) {
}
