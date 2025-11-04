package com.gad.spring_data_jpa.teacher.application.ports.input.dto;

import java.time.LocalDate;

public record UpdateTeacherCommand(
        String fullName,
        String email,
        String departmentName
) {
}
