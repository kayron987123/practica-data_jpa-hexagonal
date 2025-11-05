package com.gad.spring_data_jpa.student.infrastructure.input.rest.model.response;

public record StudentResponse(
        String fullName,
        String email,
        Integer enrollmentYear
) {
}
