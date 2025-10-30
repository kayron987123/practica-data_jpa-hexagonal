package com.gad.spring_data_jpa.enrollment.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record Enrollment(
        String studentName,
        String courseName,
        String semester,
        LocalDate enrolledDate,
        BigDecimal grade
) {
    public Enrollment {
        Objects.requireNonNull(studentName, "Student name cannot be null");
        Objects.requireNonNull(courseName, "Course name cannot be null");
        Objects.requireNonNull(semester, "Semester cannot be null");
        Objects.requireNonNull(enrolledDate, "Enrolled date cannot be null");
        Objects.requireNonNull(grade, "Grade cannot be null");

        if (studentName.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or blank");
        }

        if (courseName.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or blank");
        }

        if (semester.isBlank()) {
            throw new IllegalArgumentException("Semester cannot be null or blank");
        }

        if (enrolledDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Enrolled date cannot be in the future");
        }

        if (grade.compareTo(BigDecimal.ZERO) < 0 || grade.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
    }

    public static Enrollment create(String studentName, String courseName, String semester, LocalDate enrolledDate, BigDecimal grade) {
        return new Enrollment(studentName.trim(), courseName.trim(), semester.trim(), enrolledDate, grade);
    }

    public Enrollment withStudentName(String studentName) {
        Objects.requireNonNull(studentName, "Student name cannot be null");
        return new Enrollment(studentName.trim(), this.courseName, this.semester, this.enrolledDate, this.grade);
    }

    public Enrollment withCourseName(String courseName) {
        Objects.requireNonNull(courseName, "Course name cannot be null");
        return new Enrollment(this.studentName, courseName.trim(), this.semester, this.enrolledDate, this.grade);
    }

    public Enrollment withSemester(String semester) {
        Objects.requireNonNull(semester, "Semester cannot be null");
        return new Enrollment(this.studentName, this.courseName, semester.trim(), this.enrolledDate, this.grade);
    }

    public Enrollment withEnrolledDate(LocalDate enrolledDate) {
        Objects.requireNonNull(enrolledDate, "Enrolled date cannot be null");
        return new Enrollment(this.studentName, this.courseName, this.semester, enrolledDate, this.grade);
    }

    public Enrollment withGrade(BigDecimal grade) {
        Objects.requireNonNull(grade, "Grade cannot be null");
        return new Enrollment(this.studentName, this.courseName, this.semester, this.enrolledDate, grade);
    }

    public boolean isEnrolledInCourse(String courseName) {
        if (courseName == null || courseName.isBlank()) return false;
        return this.courseName.equalsIgnoreCase(courseName.trim());
    }
}
