package com.gad.spring_data_jpa.teacher.infrastructure.adapters.input.rest.model.response;

import java.time.LocalDate;

public record TeacherResponse(Long id,
                              String fullName,
                              String email,
                              LocalDate hireDate,
                              String departmentName) {
}
