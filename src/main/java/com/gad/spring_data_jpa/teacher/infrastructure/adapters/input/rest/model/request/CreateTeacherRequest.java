package com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateTeacherRequest(
        @NotBlank(message = "Full name cannot be blank")
        String fullName,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotBlank(message = "Department name cannot be blank")
        String departmentName
) {
}
