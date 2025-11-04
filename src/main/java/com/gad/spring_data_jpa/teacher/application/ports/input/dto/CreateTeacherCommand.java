package com.gad.spring_data_jpa.teacher.application.ports.input.dto;

import java.time.LocalDate;

public record CreateTeacherCommand(
        String fullName,
        String email,
        String departmentName
) {
}
