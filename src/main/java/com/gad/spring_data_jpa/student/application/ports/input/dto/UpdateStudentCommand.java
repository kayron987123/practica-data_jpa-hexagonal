package com.gad.spring_data_jpa.student.application.ports.input.dto;

public record UpdateStudentCommand(
        String fullName,
        String email
) {
}
