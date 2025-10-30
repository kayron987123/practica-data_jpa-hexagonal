package com.gad.spring_data_jpa.department.application.application.ports.input.dto;

public record UpdateDepartmentCommand(
        String name,
        String faculty
) {
}
