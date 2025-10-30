package com.gad.spring_data_jpa.student.domain.model;


import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public record Student(
        String fullName,
        String email,
        Integer enrollmentYear
) {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    private static final int MIN_ENROLLMENT_YEAR = 1900;

    public Student {
        Objects.requireNonNull(fullName, "Student full name cannot be null");
        Objects.requireNonNull(email, "Student email cannot be null");
        Objects.requireNonNull(enrollmentYear, "Enrollment year cannot be null");

        if (fullName.isBlank()) {
            throw new IllegalArgumentException("Student full name cannot be blank");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("Student email cannot be blank");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Student email format is invalid" + email);
        }

        int currentYear = currentYear();

        if (enrollmentYear < MIN_ENROLLMENT_YEAR) {
            throw new IllegalArgumentException(
                    "Enrollment year must be greater than or equal to " + MIN_ENROLLMENT_YEAR
            );
        }

        if (enrollmentYear > currentYear) {
            throw new IllegalArgumentException(
                    "Enrollment year cannot be in the future. Current year: " + currentYear
            );
        }
    }

    public static Student create(String fullName, String email, Integer enrollmentYear) {
        return new Student(fullName.trim(), email.trim().toLowerCase(), enrollmentYear);
    }

    public Student withFullName(String newFullName) {
        Objects.requireNonNull(newFullName, "Full name cannot be null");
        return new Student(newFullName.trim(), this.email, this.enrollmentYear);
    }

    public Student withEmail(String newEmail) {
        Objects.requireNonNull(newEmail, "Email cannot be null");
        return new Student(this.fullName, newEmail.trim().toLowerCase(), this.enrollmentYear);
    }

    public Student withEnrollmentYear(Integer newEnrollmentYear) {
        Objects.requireNonNull(newEnrollmentYear, "Enrollment year cannot be null");
        return new Student(this.fullName, this.email, newEnrollmentYear);
    }

    private static int currentYear() {
        return LocalDate.now().getYear();
    }
}
