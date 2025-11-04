package com.gad.spring_data_jpa.teacher.application.ports.input.dto;

import java.time.LocalDate;

public record TeacherDto(
        Long id,
        String fullName,
        String email,
        LocalDate hireDate,
        String departmentName
) {
}
