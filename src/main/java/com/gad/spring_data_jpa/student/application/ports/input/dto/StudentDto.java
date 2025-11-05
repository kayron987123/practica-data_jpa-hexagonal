package com.gad.spring_data_jpa.student.application.ports.input.dto;

import lombok.Builder;

@Builder
public record StudentDto(
        String fullName,
        String email,
        Integer enrollmentYear
) {
}
