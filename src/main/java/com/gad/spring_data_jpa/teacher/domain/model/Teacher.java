package com.gad.spring_data_jpa.teacher.domain.model;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

@Builder
public record Teacher(
        Long id,
        String fullName,
        String email,
        LocalDate hireDate,
        String departmentName
) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    public Teacher {
        Objects.requireNonNull(fullName, "Teacher full name cannot be null");
        Objects.requireNonNull(email, "Teacher email cannot be null");
        Objects.requireNonNull(hireDate, "Hire date cannot be null");
        Objects.requireNonNull(departmentName, "Department name cannot be null");

        if (fullName.isBlank()) {
            throw new IllegalArgumentException("Teacher full name cannot be blank");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("Teacher email cannot be blank");
        }

        if (departmentName.isBlank()) {
            throw new IllegalArgumentException("Department name cannot be blank");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Teacher email format is invalid: " + email);
        }

        if (hireDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hire date cannot be in the future.");
        }
    }

    public static Teacher create(Long id, String fullName, String email, LocalDate hireDate, String departmentName) {
        return new Teacher(id, fullName.trim(), email.trim().toLowerCase(), hireDate, departmentName.trim());
    }

    public Teacher withFullName(String newFullName) {
        Objects.requireNonNull(newFullName, "Full name cannot be null");
        return new Teacher(this.id, newFullName.trim(), this.email, this.hireDate, this.departmentName);
    }

    public Teacher withEmail(String newEmail) {
        Objects.requireNonNull(newEmail, "Email cannot be null");
        return new Teacher(this.id, this.fullName, newEmail.trim().toLowerCase(), this.hireDate, this.departmentName);
    }

    public Teacher withHireDate(LocalDate newHireDate) {
        Objects.requireNonNull(newHireDate, "Hire date cannot be null");
        return new Teacher(this.id, this.fullName, this.email, newHireDate, this.departmentName);
    }

    public Teacher withDepartmentName(String departmentName) {
        Objects.requireNonNull(departmentName, "Department name cannot be null");
        return new Teacher(this.id, this.fullName, this.email, this.hireDate, departmentName);
    }
}