package com.gad.spring_data_jpa.course.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.*;

public record CreateCourseRequest(
        @NotBlank(message = "Course name cannot be blank")
        @Size(min = 3, max = 50, message = "Course name must be between 3 and 50 characters")
        String name,

        @NotNull(message = "Credits cannot be null")
        @Min(value = 1, message = "Credits must be at least 1")
        @Max(value = 10, message = "Credits must be at most 10")
        Integer credits,

        @NotBlank(message = "Department name cannot be blank")
        @Size(min = 3, max = 50, message = "Department name must be between 3 and 50 characters")
        String departmentName
) {
}
