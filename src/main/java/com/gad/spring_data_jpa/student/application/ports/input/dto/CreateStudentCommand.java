package com.gad.spring_data_jpa.student.application.ports.input.dto;

public record CreateStudentCommand(
        String fullName,
        String email
) {
}
