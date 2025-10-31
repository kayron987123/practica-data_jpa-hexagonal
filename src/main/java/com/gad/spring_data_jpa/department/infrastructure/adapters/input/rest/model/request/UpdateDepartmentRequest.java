package com.gad.spring_data_jpa.department.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateDepartmentRequest(
        @NotBlank(message = "Department name must not be blank")
        @Size(min = 3, max = 50, message = "Department name must be between 3 and 50 characters")
        String name,

        @NotBlank(message = "Faculty must not be blank")
        @Size(min = 3, max = 50, message = "Faculty must be between 3 and 50 characters")
        String faculty
) {
}
