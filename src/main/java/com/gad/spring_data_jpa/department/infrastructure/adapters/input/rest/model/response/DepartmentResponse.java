package com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.response;

public record DepartmentResponse(
        Long id,
        String name,
        String faculty
) {
}
