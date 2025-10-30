package com.gad.spring_data_jpa.teacher.domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public record Teacher(
        String fullName,
        String email,
        LocalDate hireDate
) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    public Teacher {
        Objects.requireNonNull(fullName, "Teacher full name cannot be null");
        Objects.requireNonNull(email, "Teacher email cannot be null");
        Objects.requireNonNull(hireDate, "Hire date cannot be null");

        if (fullName.isBlank()) {
            throw new IllegalArgumentException("Teacher full name cannot be blank");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("Teacher email cannot be blank");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Teacher email format is invalid: " + email);
        }

        if (hireDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hire date cannot be in the future.");
        }
    }

    public static Teacher create(String fullName, String email, LocalDate hireDate) {
        return new Teacher(fullName.trim(), email.trim().toLowerCase(), hireDate);
    }

    public Teacher withFullName(String newFullName) {
        Objects.requireNonNull(newFullName, "Full name cannot be null");
        return new Teacher(newFullName.trim(), this.email, this.hireDate);
    }

    public Teacher withEmail(String newEmail) {
        Objects.requireNonNull(newEmail, "Email cannot be null");
        return new Teacher(this.fullName, newEmail.trim().toLowerCase(), this.hireDate);
    }

    public Teacher withHireDate(LocalDate newHireDate) {
        Objects.requireNonNull(newHireDate, "Hire date cannot be null");
        return new Teacher(this.fullName, this.email, newHireDate);
    }
}