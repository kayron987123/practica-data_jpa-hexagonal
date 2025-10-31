package com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.request;

public record CreateDepartmentRequest(
        String name,
        String faculty
) {
}
